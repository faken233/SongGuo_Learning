"use strict";(self["webpackChunkworkspace"]=self["webpackChunkworkspace"]||[]).push([[484],{6484:function(e,t,a){a.r(t),a.d(t,{default:function(){return c}});var s=function(){var e=this,t=e._self._c;return t("div",[t("el-container",[t("el-header",{staticStyle:{"text-align":"right"}}),t("el-container",[t("el-aside",{staticStyle:{"background-color":"rgb(238, 241, 246)"},attrs:{width:"200px"}},[t("el-menu",{attrs:{"default-openeds":["1"],router:"true"}},[t("el-submenu",{attrs:{index:"1"}},[t("template",{slot:"title"},[t("i",{staticClass:"el-icon-message"}),e._v("菜单")]),t("el-menu-item-group",[t("el-menu-item",{attrs:{index:"/student_homepage_personal_info"}},[e._v("个人信息")]),t("el-menu-item",{attrs:{index:"/student_course_choose_to_learn"}},[e._v("课程学习")]),t("el-menu-item",{attrs:{index:"/student_course_select"}},[e._v("选择课程")])],1)],2)],1)],1),t("el-main",[t("el-table",{staticStyle:{width:"100%"},attrs:{data:e.courses}},[t("el-table-column",{attrs:{prop:"courseID",label:"课程ID",width:"180"}}),t("el-table-column",{attrs:{prop:"courseName",label:"课程名",width:"180"}}),t("el-table-column",{attrs:{prop:"description",label:"课程说明"}}),t("el-table-column",{attrs:{label:"操作",width:"180"},scopedSlots:e._u([{key:"default",fn:function(a){return[t("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(t){return e.participate(a.row)}}},[e._v("参加")])]}}])})],1)],1)],1)],1)],1)},l=[],o=(a(4114),a(8355)),n={data(){return{courses:[]}},methods:{participate(e){let t=e.courseID;o.A.get("http://localhost:8080/SongGuo_Learning/student/participateCourse?studentID="+localStorage.getItem("id")+"&courseID="+t,{headers:{Token:localStorage.getItem("token")}}).then((e=>{console.log(e.data),"ENROLL_OK"==e.data.message?this.$notify.success({title:"加入课程成功",message:"您已经成功加入课程!请刷新页面..."}):"MAX_STUDENT"==e.data.message&&this.$notify.info({title:"加入课程失败",message:"课程已满员!请选择其他感兴趣的课程"})}))}},mounted(){let e=localStorage.getItem("token");o.A.get("http://localhost:8080/SongGuo_Learning/student/selectParticipatableCourses?studentID="+localStorage.getItem("id"),{headers:{Token:e}}).then((e=>{"TOKEN_IS_NULL"!=e.data.message?this.courses=e.data.data:this.$router.push("/studentlogin")}))}},r=n,i=a(1656),u=(0,i.A)(r,s,l,!1,null,null,null),c=u.exports}}]);
//# sourceMappingURL=484.d48e0019.js.map