package cn.lubang.lubangcommon.controller;

import cn.lubang.lubangcommon.entity.SysMenuEntity;
import cn.lubang.lubangcommon.entity.SysUserEntity;
import cn.lubang.lubangcommon.service.SysMenuService;
import cn.lubang.lubangcommon.utils.PageUtils;
import cn.lubang.lubangcommon.utils.Query;
import cn.lubang.lubangcommon.utils.R;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys/sys_menu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping("/list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<SysMenuEntity> menuList = sysMenuService.queryList(query);
        int total = sysMenuService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(menuList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 所有菜单列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {
        //查询列表数据
        List<SysMenuEntity> menuList = sysMenuService.queryList(params);

        return R.ok().put("list", menuList);
    }
    /**
     * 所有菜单列表
     */
    @RequestMapping("/userMenuList")
    @ResponseBody
    public R getUserMenuList(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Subject subject = SecurityUtils.getSubject();
        SysUserEntity user = (SysUserEntity) subject.getPrincipal();

        List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(user.getId());

        return R.ok().put("list", menuList);
    }
}
