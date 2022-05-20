package com.wyd.wenyongdaCredit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.wyd.wenyongdaCredit.model.dto.CustomerDTO;
import com.wyd.wenyongdaCredit.model.entity.CustomerEntity;

import java.util.List;

public interface ICustomerService extends IService<CustomerEntity> {

    PageInfo<CustomerEntity> selectCustomerWithPageCount(Integer pageNumber, Integer pageSize, CustomerDTO customerDTO);

    Integer addCustomer(CustomerDTO customerDTO);

    Integer updateCustomer(CustomerDTO customerDTO);

    Integer deleteCustomer(List<String> ids);
}
