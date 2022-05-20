package com.wyd.wenyongdaCredit.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyd.wenyongdaCredit.mapper.CustomerMapper;
import com.wyd.wenyongdaCredit.model.dto.CustomerDTO;
import com.wyd.wenyongdaCredit.model.entity.CustomerEntity;
import com.wyd.wenyongdaCredit.service.ICustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, CustomerEntity> implements ICustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Override
    public PageInfo<CustomerEntity> selectCustomerWithPageCount(Integer pageNumber, Integer pageSize, CustomerDTO customerDTO) {
        if (pageNumber != 0 && pageSize != 0) {
            PageHelper.startPage(pageNumber, pageSize);
            List<CustomerEntity> userInfoVOList = this.baseMapper.selectList(customerQueryWrapper(customerDTO));
            PageInfo<CustomerEntity> userInfoVOPageInfo = new PageInfo<>(userInfoVOList);
            PageHelper.clearPage();
            return userInfoVOPageInfo;
        } else {
            return null;
        }
    }

    @Override
    public Integer addCustomer(CustomerDTO customerDTO) {
        if (ObjectUtil.isNotNull(customerDTO)) {
            Snowflake snowflake = IdUtil.createSnowflake(1, 1);
            //customerDTO.setId(IdUtil.simpleUUID());
            customerDTO.setId(snowflake.nextIdStr());
            CustomerEntity customer = new CustomerEntity();
            BeanUtil.copyProperties(customerDTO, customer);
            return this.baseMapper.insert(customer);
        } else {
            return 0;
        }
    }

    @Override
    public Integer updateCustomer(CustomerDTO customerDTO) {
        if (ObjectUtil.isNotNull(customerDTO)) {
            return this.baseMapper.update(new CustomerEntity(), customerUpdateWrapper(customerDTO));
        } else {
            return 0;
        }
    }

    @Override
    public Integer deleteCustomer(List<String> ids) {
        if (ObjectUtil.isNotNull(ids) && ids.size() > 0) {
            return this.baseMapper.deleteBatchIds(ids);
        } else {
            return 0;
        }
    }

    private UpdateWrapper<CustomerEntity> customerUpdateWrapper(CustomerDTO customerDTO) {
        if (ObjectUtil.isNotNull(customerDTO)) {
            UpdateWrapper<CustomerEntity> updateWrapper = new UpdateWrapper<>();
            if (StrUtil.isNotBlank(String.valueOf(customerDTO.getId()))) {
                updateWrapper.eq("id", customerDTO.getId());
            }
            if (StrUtil.isNotBlank(customerDTO.getCustomerName())) {
                updateWrapper.set("customer_name", customerDTO.getCustomerName());
            }
            if (StrUtil.isNotBlank(customerDTO.getIdNumber())) {
                updateWrapper.set("id_number", customerDTO.getIdNumber());
            }
            if (ObjectUtil.isNotNull(customerDTO.getIdType())) {
                updateWrapper.set("id_type", customerDTO.getIdType());
            }
            return updateWrapper;
        }
        return null;
    }

    private QueryWrapper<CustomerEntity> customerQueryWrapper(CustomerDTO customerDTO) {
        if (ObjectUtil.isNotNull(customerDTO)) {
            QueryWrapper<CustomerEntity> queryWrapper = new QueryWrapper<>();
            if (StrUtil.isNotBlank(String.valueOf(customerDTO.getId()))) {
                queryWrapper.eq("id", customerDTO.getId());
            }
            if (StrUtil.isNotBlank(customerDTO.getCustomerName())) {
                queryWrapper.eq("customer_name", customerDTO.getCustomerName());
            }
            if (StrUtil.isNotBlank(customerDTO.getIdNumber())) {
                queryWrapper.eq("id_number", customerDTO.getIdNumber());
            }
            if (ObjectUtil.isNotNull(customerDTO.getIdType())) {
                queryWrapper.eq("id_type", customerDTO.getIdType());
            }
            return queryWrapper;
        }
        return null;
    }
}
