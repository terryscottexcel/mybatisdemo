package com.excel.demo.mapper;

import com.excel.demo.model.FilmFocus;
import java.util.List;
import java.util.Map;

public interface FilmFocusMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_filmfocus
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_filmfocus
     *
     * @mbg.generated
     */
    int insert(FilmFocus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_filmfocus
     *
     * @mbg.generated
     */
    FilmFocus selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_filmfocus
     *
     * @mbg.generated
     */
    List<FilmFocus> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_filmfocus
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(FilmFocus record);
    
    /**
     * This method was generated by Terry.
     * This method corresponds to the database table t_fansinfo
     *
     * @mbg.generated
     */
    List<FilmFocus> selectAllByUserId(Integer userId);
    
    /**
     * This method was generated by Terry.
     * This method corresponds to the database table t_fansinfo
     *
     * @mbg.generated
     */
    List<FilmFocus> selectAllByName(String filmName);
    
    /**
     * This method was generated by Terry.
     * This method corresponds to the database table t_fansinfo
     *
     * @mbg.generated
     */
    Integer selectAllCountByParam(Map<String, Object> fileMap);
    
    /**
     * This method was generated by Terry.
     * This method corresponds to the database table t_fansinfo
     *
     * @mbg.generated
     */
    List<FilmFocus> selectAllByParam(Map<String, Object> fileMap);
}