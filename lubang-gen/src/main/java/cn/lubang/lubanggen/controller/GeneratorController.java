package cn.lubang.lubanggen.controller;

import cn.lubang.lubangcommon.utils.PageUtils;
import cn.lubang.lubangcommon.utils.Query;
import cn.lubang.lubangcommon.utils.R;
import cn.lubang.lubangcommon.xss.XssHttpServletRequestWrapper;
import cn.lubang.lubanggen.dto.GeneratorDto;
import cn.lubang.lubanggen.service.GeneratorService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2017年1月3日 下午6:35:28
 */
@Controller
@RequestMapping("/admin/sys/generator")
public class GeneratorController {
    @Autowired
    private GeneratorService GeneratorService;

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/list")
//    @RequiresPermissions("sys:generator:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<Map<String, Object>> list = GeneratorService.queryList(query);
        int total = GeneratorService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 生成代码
     */
    @RequestMapping("/code")
//    @RequiresPermissions("sys:generator:code")
    public void code(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取表名，不进行xss过滤
        HttpServletRequest orgRequest = XssHttpServletRequestWrapper.getOrgRequest(request);
        // 获取请求对象
        GeneratorDto generatorDto = reqToDto(orgRequest);
        byte[] data = GeneratorService.generatorCode(generatorDto);

        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"" + generatorDto.getTableNames() + "-AutoCode.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }


    private GeneratorDto reqToDto(HttpServletRequest request) {
        GeneratorDto dto = new GeneratorDto();
        dto.setAuthor(request.getParameter("author"));
        dto.setMail(request.getParameter("mail"));
        dto.setModule(request.getParameter("module"));
        dto.setPackName(request.getParameter("packName"));
        dto.setTableNames(request.getParameter("tableNames"));
        dto.setMenuName(request.getParameter("menuName"));
        dto.setParentId(request.getParameter("parentId"));
        return dto;
    }
}
