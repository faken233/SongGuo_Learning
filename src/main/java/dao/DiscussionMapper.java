package dao;

import pojo.DiscussionReply;
import utils.mybatis.anno.DeleteFaken;
import utils.mybatis.anno.InsertFaken;
import utils.mybatis.anno.ParamFaken;
import utils.mybatis.anno.SelectFaken;

import java.sql.Timestamp;
import java.util.List;

public interface DiscussionMapper {
    // 添加一条reply
    @InsertFaken("insert into discussionreplies (replierID, courseID, replierName, content, replyDateTime) " +
            "values (#{replierID}, #{courseID}, #{replierName}, #{content}, NOW())")
    void addOneReplyToCourseDiscussion(@ParamFaken("replierID")   Integer replierID,
                                       @ParamFaken("courseID")    Integer courseID,
                                       @ParamFaken("replierName") String replierName,
                                       @ParamFaken("content")     String content);

    // 查询课程下的所有reply, 分页并且按照回复日期升序排序
    @SelectFaken("select * from discussionreplies where courseID = #{courseID} order by replyDateTime limit #{offset}, #{pageSize}")
    List<DiscussionReply> getCourseReplies(@ParamFaken("courseID") Integer courseID,
                                           @ParamFaken("offset")   Integer offset,
                                           @ParamFaken("pageSize") Integer pageSize);

    // 删除某条reply 由于取消了reply的primary key 故需要比对课程ID, 回复者ID以及回复时间
    @DeleteFaken("delete from discussionreplies where ")
    void deleteOneReplyInCourse(@ParamFaken("courseID")      Integer courseID,
                                @ParamFaken("replierID")     Integer replierID,
                                @ParamFaken("replyDateTime") Timestamp replyDateTime);
}
