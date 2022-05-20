package com.wyd.wenyongdaCredit.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageInfo;
import com.wyd.wenyongdaCredit.commons.util.ResultEnum;
import com.wyd.wenyongdaCredit.commons.util.ResultUtil;
import com.wyd.wenyongdaCredit.model.dto.CustomerDTO;
import com.wyd.wenyongdaCredit.model.dto.RatingTemplateDTO;
import com.wyd.wenyongdaCredit.model.entity.CustomerEntity;
import com.wyd.wenyongdaCredit.model.entity.RatingTemplateEntity;
import com.wyd.wenyongdaCredit.service.ICustomerService;
import com.wyd.wenyongdaCredit.service.IRatingTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
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
@RequestMapping("/rating_template")
@RefreshScope
public class RatingTemplateController {
    private static final Logger log = LoggerFactory.getLogger(RatingTemplateController.class);

    private IRatingTemplateService ratingTemplateService;

    @Autowired
    public void setRatingTemplateService(IRatingTemplateService ratingTemplateService) {
        this.ratingTemplateService = ratingTemplateService;
    }


    @ApiOperation("添加用户")
    @PostMapping("/save")
    public ResultUtil addRatingTemplate(@RequestBody Map<String, Object> reqParams) {
        if (ObjectUtil.isNotNull(reqParams.get("templateName")) && ObjectUtil.isNotNull(reqParams.get("financialAccounting"))
            && ObjectUtil.isNotNull(reqParams.get("templateType")) &&  ObjectUtil.isNotNull(reqParams.get("templateTermType"))) {
            String templateName = (String) reqParams.get("templateName");
            String templateTermType = (String) reqParams.get("templateTermType");
            Integer templateType = (Integer) reqParams.get("templateType");
            Integer financialAccounting = (Integer) reqParams.get("financialAccounting");
            Integer nonFinancialAccounting = (Integer) reqParams.get("nonFinancialAccounting");
            Integer financialNonFinancialScoreGap = (Integer) reqParams.get("financialNonFinancialScoreGap");
            Integer isEnabled = (Integer) reqParams.get("isEnabled");
            Integer res = this.ratingTemplateService.addRatingTemplate(new RatingTemplateDTO()
                    .setTemplateName(templateName)
                    .setTemplateType(templateType)
                    .setTemplateTermType(templateTermType)
                    .setFinancialAccounting(financialAccounting)
                            .setNonFinancialAccounting(nonFinancialAccounting)
                    .setFinancialNonFinancialScoreGap(financialNonFinancialScoreGap)
                    .setIsEnabled(isEnabled));
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
    public ResultUtil selectRatingTemplatePageWithCount(@RequestBody Map<String, Object> reqParams) {
        if (ObjectUtil.isNotNull(reqParams.get("pageNumber")) && ObjectUtil.isNotNull(reqParams.get("pageSize"))) {
            Integer pageNumber = (Integer) reqParams.get("pageNumber");
            Integer pageSize = (Integer) reqParams.get("pageSize");
            //Long id = (Long)
            PageInfo<RatingTemplateEntity> pageInfo = this.ratingTemplateService.selectRatingTemplateWithPageCount(pageNumber, pageSize,
                    ratingTemplateDTOIfHasStrBlankToNull(reqParams));
            return ResultUtil.ok().data("records",pageInfo);
        } else {
            return ResultUtil.error(ResultEnum.PAGE_NUMBER_OR_PAGE_SIZE_IS_EMPTY);
        }
    }


    @ApiOperation("更新用户")
    @PutMapping("/update")
    public ResultUtil updateRatingTemplate(@RequestBody Map<String, Object> reqParams) {
        if (ObjectUtil.isNotNull(reqParams.get("id"))) {
            RatingTemplateDTO ratingTemplateDTO = BeanUtil.fillBeanWithMap(reqParams, new RatingTemplateDTO(), false);
            Integer updateCustomerRes = this.ratingTemplateService.updateRatingTemplate(ratingTemplateDTO);
            if (updateCustomerRes > 0) {
                return ResultUtil.ok();
            } else {
                return ResultUtil.error(ResultEnum.DICT_ENTRY_ERROR_OR_UNKNOWN);
            }
        } else {
            return ResultUtil.error(ResultEnum.USER_ID_IS_EMPTY);
        }
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/delete")
    public ResultUtil deleteRatingTemplate(@RequestBody Map<String, Object> reqParams) {
        if (ObjectUtil.isNotNull(reqParams.get("ids"))) {
            List<String> ids = Convert.toList(String.class, reqParams.get("ids"));
            if (this.ratingTemplateService.deleteRatingTemplate(ids) > 0 ) {
                return ResultUtil.ok();
            } else {
                return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
            }
        } else {
            return ResultUtil.error(ResultEnum.USER_ID_IS_EMPTY);
        }
    }

    private RatingTemplateDTO ratingTemplateDTOIfHasStrBlankToNull(Map<String, Object> reqParams) {
        RatingTemplateDTO ratingTemplateDTO = new RatingTemplateDTO();
        if (StrUtil.isNotBlank((String) reqParams.get("id"))) {
            Long id = Long.valueOf((String) reqParams.get("id"));
            ratingTemplateDTO.setId(id);
        }
        String templateName = (String) reqParams.get("templateName");
        ratingTemplateDTO.setTemplateName(templateName);

        if (ObjectUtil.isNotNull(reqParams.get("templateType"))) {
            ratingTemplateDTO.setTemplateType((Integer) reqParams.get("templateType"));
        }
        //Integer idType = Integer.valueOf(reqParams.get("idType").toString());
        //customerDTO.setIdType(idType);
        //if (StrUtil.isNotBlank((String) reqParams.get("id"))) {
        //    String id = (String) reqParams.get("id");
        //    customerDTO.setId(id);
        //}
        //if (StrUtil.isNotBlank((String) reqParams.get("customerName"))) {
        //    String customerName = (String) reqParams.get("customerName");
        //    customerDTO.setCustomerName(customerName);
        //}
        //if (StrUtil.isNotBlank((String) reqParams.get("idNumber"))) {
        //    String idNumber = (String) reqParams.get("idNumber");
        //    customerDTO.setIdNumber(idNumber);
        //}
        return ratingTemplateDTO;
    }
}
