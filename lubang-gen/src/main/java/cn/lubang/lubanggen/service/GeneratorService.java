package cn.lubang.lubanggen.service;

import cn.lubang.lubanggen.dto.GeneratorDto;

import java.util.List;
import java.util.Map;

public interface GeneratorService {
    List<Map<String, Object>> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    Map<String, String> queryTable(String tableName);

    List<Map<String, Object>> queryColumns(String tableName);

    /**
     * 生成代码
     * @param request
     */
    byte[] generatorCode(GeneratorDto request);
}
