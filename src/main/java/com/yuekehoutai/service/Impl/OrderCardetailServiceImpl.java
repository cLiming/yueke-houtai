package com.yuekehoutai.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuekehoutai.Dto.CarDto;
import com.yuekehoutai.Dto.OrderRentDetailDto;
import com.yuekehoutai.domain.Car;
import com.yuekehoutai.domain.OrderCardetail;
import com.yuekehoutai.domain.Worker;
import com.yuekehoutai.mapper.CarMapper;
import com.yuekehoutai.mapper.OrderCardetailMapper;
import com.yuekehoutai.mapper.OrderMapper;
import com.yuekehoutai.mapper.WorkerMapper;
import com.yuekehoutai.service.OrderCardetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuekehoutai.util.DateUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author corazon
 * @since 2020-11-10
 */
@Service
public class OrderCardetailServiceImpl extends ServiceImpl<OrderCardetailMapper, OrderCardetail> implements OrderCardetailService {
    @Resource
    private OrderCardetailMapper orderCardetailMapper;
    @Resource
    private CarMapper carMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private WorkerMapper workerMapper;
    public Page<Car> selectCampCar(CarDto carDto, Page<Car> page) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        Worker worker = (Worker)subject.getPrincipal();
        if(worker!=null){
            //获取当前管理员的信息 查询所属营地
            QueryWrapper<Worker> workerQueryWrapper = new QueryWrapper<>();
            String tel = worker.getTel();
            workerQueryWrapper.eq("tel", tel);
            Worker worker1 = workerMapper.selectOne(workerQueryWrapper);
            if(worker1!=null&&worker1.getCId()!=null){
                //通过这个营地管理员营地id 查询这个营地的再租的房车的信息
                QueryWrapper<Car> carQueryWrapper = new QueryWrapper<>();
                carQueryWrapper.eq("c_id", worker1.getCId());
                carQueryWrapper.eq("status", 1);
                Page<Car> carPage = carMapper.selectPage(page, carQueryWrapper);
                return carPage;
            }
        }
        return null;
    }

    @Override
    public List<OrderRentDetailDto> selectTimeCarOrder(OrderRentDetailDto orderRentDetailDto) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        Worker worker = (Worker)subject.getPrincipal();
        if(worker!=null){
            //获取当前管理员的信息 查询所属营地
            QueryWrapper<Worker> workerQueryWrapper = new QueryWrapper<>();
            String tel = worker.getTel();
            workerQueryWrapper.eq("tel", tel);
            Worker worker1 = workerMapper.selectOne(workerQueryWrapper);
            if(worker1!=null&&worker1.getCId()!=null){
                //通过这个营地管理员营地id 查询这个营地的再租的房车的信息
                QueryWrapper<Car> carQueryWrapper = new QueryWrapper<>();
                carQueryWrapper.eq("c_id", worker1.getCId());
                carQueryWrapper.eq("status", 1);
                List<Car> cars = carMapper.selectList(carQueryWrapper);
                //查询这些房车的订单详情
                QueryWrapper<OrderCardetail> orderCardetailQueryWrapper = new QueryWrapper<>();
                ArrayList<OrderRentDetailDto> orderRentDetailDtos = new ArrayList<>();
                for(Car car:cars){
                    OrderRentDetailDto orderRentDetailDto1 = new OrderRentDetailDto();
                    orderCardetailQueryWrapper.eq("typ",5);
                    orderCardetailQueryWrapper.eq("x_id", car.getId());
                    orderCardetailQueryWrapper.ge(orderRentDetailDto.getEndTime()!=null,"end_time", DateUtil.getStartTimestamp());
                    orderCardetailQueryWrapper.le(orderRentDetailDto.getEndTime()!=null,"end_time", DateUtil.getEndTimestamp());
                    OrderCardetail orderCardetail = orderCardetailMapper.selectOne(orderCardetailQueryWrapper);
                    BeanUtils.copyProperties(orderCardetail, orderRentDetailDto1);
                    orderRentDetailDtos.add(orderRentDetailDto1);
                }
                return orderRentDetailDtos;
            }
        }
        return null;
    }

    @Override
    public void addCarOrderTime(OrderRentDetailDto orderRentDetailDto) throws Exception {
        OrderCardetail orderCardetail = new OrderCardetail();
        UpdateWrapper<OrderCardetail> orderCardetailUpdateWrapper = new UpdateWrapper<>();
        orderCardetailUpdateWrapper.eq("id", orderRentDetailDto.getId());
        orderCardetailUpdateWrapper.set("end_time", orderRentDetailDto.getEndTime());
        orderCardetailMapper.update(null, orderCardetailUpdateWrapper);
    }
}
