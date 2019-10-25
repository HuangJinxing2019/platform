package cn.lubang.lubanggen.service.impl;

import cn.lubang.lubanggen.dao.GeneratorDao;
import cn.lubang.lubanggen.dto.GeneratorDto;
import cn.lubang.lubanggen.service.GeneratorService;
import cn.lubang.lubanggen.utils.GenUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

@Service("generatorService")
public class GeneratorImpl implements GeneratorService {

    @Autowired
    private GeneratorDao generatorDao;


    @Override
    public List<Map<String, Object>> queryList(Map<String, Object> map) {
        //如果是ORACLE数据库转为驼峰命名
        return generatorDao.queryList(map);

    }

    @Override
    public int queryTotal(Map<String, Object> map) {

        return generatorDao.queryTotal(map);
    }

    @Override
    public Map<String, String> queryTable(String tableName) {

        return generatorDao.queryTable(tableName);
    }

    @Override
    public List<Map<String, Object>> queryColumns(String tableName) {
        return generatorDao.queryColumns(tableName);
    }

    private void loadColumnInfo(List<Map<String, Object>> columns) {
        for (Map<String, Object> column : columns) {
            String comment = (String) column.get("columnComment");
            // 判断列类型
            if (comment != null) {
                if (comment.contains("ref:")) {
                    column.put("code", GenUtils.subCode(comment, "ref:"));
                    column.put("type", "enum");
//                    column.put("labelList", sysBizLabelService.queryByCode((String) column.get("code")));
                    column.put("remark", GenUtils.subRemark(comment, "ref:"));
                } else if (comment.contains("fk:")) {
                    column.put("code", GenUtils.subCode(comment, "pk:"));
                    column.put("type", "fk");
                    column.put("remark", GenUtils.subRemark(comment, "pk:"));
                } else {
                    column.put("remark", comment);
                }
            }
        }
    }

    @Override
    public byte[] generatorCode(GeneratorDto generatorDto) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        String[] tableNames = generatorDto.getTableNames().split(",");

        for (String tableName : tableNames) {
            //查询表信息
            Map<String, String> table = queryTable(tableName);
            //查询列信息
            List<Map<String, Object>> columns = queryColumns(tableName);
            //加载列信息
            this.loadColumnInfo(columns);
            //生成代码
            GenUtils.generatorCode(table, columns, zip, generatorDto);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }
}
