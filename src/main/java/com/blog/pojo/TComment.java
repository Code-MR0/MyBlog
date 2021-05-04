package com.blog.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author MR
 * @since 2021-04-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TComment对象", description="")
public class TComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评论id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "评论者名称")
    private String name;

    @ApiModelProperty(value = "评论者邮箱")
    private String email;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "评论文章id")
    private Long articleId;

    @ApiModelProperty(value = "回复id")
    private Long replyId;

    @ApiModelProperty(value = "回复祖先id")
    private Long replyAncestorId;

    @ApiModelProperty(value = "回复的人的昵称")
    private String replyName;

    @ApiModelProperty(value = "评论时间")
    @TableField(fill = FieldFill.INSERT)
    private Date publishDate;

    @ApiModelProperty(value = "子评论列表")
    @TableField(exist = false)
    private List<TComment> sonComment;

    @ApiModelProperty(value = "处理后的评论时间")
    @TableField(exist = false)
    private String replyDate;

}
