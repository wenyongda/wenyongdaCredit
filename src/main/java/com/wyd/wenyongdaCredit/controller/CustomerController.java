package com.wyd.wenyongdaCredit.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageInfo;
import com.wyd.wenyongdaCredit.commons.util.ResultEnum;
import com.wyd.wenyongdaCredit.commons.util.ResultUtil;
import com.wyd.wenyongdaCredit.model.dto.CustomerDTO;
import com.wyd.wenyongdaCredit.model.entity.CustomerEntity;
import com.wyd.wenyongdaCredit.service.ICustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wenyongda
 * @since 2021-10-18
 */
@Api(tags = "用户中心")
@RestController
@RequestMapping("/customer")
@RefreshScope
public class CustomerController {
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private ICustomerService customerService;

    @Autowired
    public void setUserAccountService(ICustomerService customerService) {
        this.customerService = customerService;
    }


    @ApiOperation("添加用户")
    @PostMapping("/save")
    public ResultUtil addCustomer(@RequestBody Map<String, Object> reqParams) {
        if (ObjectUtil.isNotNull(reqParams.get("customerName")) &&
        ObjectUtil.isNotNull(reqParams.get("idType")) &&  ObjectUtil.isNotNull(reqParams.get("idNumber"))) {
            String customerName = (String) reqParams.get("customerName");
            String idNumber = (String) reqParams.get("idNumber");
            Integer idType = (Integer) reqParams.get("idType");
            Integer res = this.customerService.addCustomer(new CustomerDTO().setCustomerName(customerName).setIdNumber(idNumber)
                    .setIdType(idType));
            if (res > 0) {
                return ResultUtil.ok();
            } else {
                return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
            }
        } else {
            return ResultUtil.error(ResultEnum.USERNAME_OR_NICKNAME_IS_EMPTY);
        }
    }

    @ApiOperation("分页查询用户列表")
    @PostMapping("/getAll")
    public ResultUtil selectUserPageWithCount(@RequestBody Map<String, Object> reqParams) {
        if (ObjectUtil.isNotNull(reqParams.get("pageNumber")) && ObjectUtil.isNotNull(reqParams.get("pageSize"))) {
            Integer pageNumber = (Integer) reqParams.get("pageNumber");
            Integer pageSize = (Integer) reqParams.get("pageSize");
            PageInfo<CustomerEntity> pageInfo = this.customerService.selectCustomerWithPageCount(pageNumber, pageSize, customerDTOIfHasStrBlankToNull(reqParams));
            return ResultUtil.ok().data("records",pageInfo);
        } else {
            return ResultUtil.error(ResultEnum.PAGE_NUMBER_OR_PAGE_SIZE_IS_EMPTY);
        }
    }


    @ApiOperation("更新用户")
    @PutMapping("/update")
    public ResultUtil updateCustomer(@RequestBody Map<String, Object> reqParams) {
        if (ObjectUtil.isNotNull(reqParams.get("id"))) {
            CustomerDTO customerDTO = BeanUtil.fillBeanWithMap(reqParams, new CustomerDTO(), false);
            Integer updateCustomerRes = this.customerService.updateCustomer(customerDTO);
            if (updateCustomerRes > 0) {
                return ResultUtil.ok();
            } else {
                //return "13114";
                return ResultUtil.error(ResultEnum.DICT_ENTRY_ERROR_OR_UNKNOWN);
                //return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
            }
        } else {
            return ResultUtil.error(ResultEnum.USER_ID_IS_EMPTY);
        }
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/delete")
    public ResultUtil deleteCustomer(@RequestBody Map<String, Object> reqParams) {
        if (ObjectUtil.isNotNull(reqParams.get("ids"))) {
            List<String> ids = Convert.toList(String.class, reqParams.get("ids"));
            if (this.customerService.deleteCustomer(ids) > 0 ) {
                return ResultUtil.ok();
            } else {
                return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
            }
        } else {
            return ResultUtil.error(ResultEnum.USER_ID_IS_EMPTY);
        }
    }

    private CustomerDTO customerDTOIfHasStrBlankToNull(Map<String, Object> reqParams) {
        CustomerDTO customerDTO = new CustomerDTO();
        String id = (String) reqParams.get("id");
        customerDTO.setId(id);
        String customerName = (String) reqParams.get("customerName");
        customerDTO.setCustomerName(customerName);
        //Integer idType = Integer.valueOf(reqParams.get("idType").toString());
        //customerDTO.setIdType(idType);
        String idNumber = (String) reqParams.get("idNumber");
        customerDTO.setIdNumber(idNumber);
        //if (StrUtil.isNotBlank((String) reqParams.get("id"))) {
        //    String id = (String) reqParams.get("id");
        //    customerDTO.setId(id);
        //}
        //if (StrUtil.isNotBlank((String) reqParams.get("customerName"))) {
        //    String customerName = (String) reqParams.get("customerName");
        //    customerDTO.setCustomerName(customerName);
        //}
        if (StrUtil.isNotBlank(String.valueOf(reqParams.get("idType")))) {
            Integer idType = (Integer) reqParams.get("idType");
            customerDTO.setIdType(idType);
        }
        //if (StrUtil.isNotBlank((String) reqParams.get("idNumber"))) {
        //    String idNumber = (String) reqParams.get("idNumber");
        //    customerDTO.setIdNumber(idNumber);
        //}
        return customerDTO;
    }
}
