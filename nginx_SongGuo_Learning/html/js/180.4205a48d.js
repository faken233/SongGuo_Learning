"use strict";(self["webpackChunkworkspace"]=self["webpackChunkworkspace"]||[]).push([[180],{1180:function(e,t,a){a.r(t),a.d(t,{default:function(){return c}});var r=function(){var e=this,t=e._self._c;return t("div",[t("el-container",[t("el-header",{staticStyle:{"text-align":"right"}}),t("el-container",[t("el-aside",{staticStyle:{"background-color":"rgb(238, 241, 246)"},attrs:{width:"200px"}},[t("el-menu",{attrs:{"default-openeds":["1"],router:"true"}},[t("el-submenu",{attrs:{index:"1"}},[t("template",{slot:"title"},[t("i",{staticClass:"el-icon-message"}),e._v("菜单")]),t("el-menu-item-group",[t("el-menu-item",{attrs:{index:"/teacher_homepage_personal_info"}},[e._v("个人信息")]),t("el-menu-item",{attrs:{index:"/teacher_courses_manage"}},[e._v("课程管理")]),t("el-menu-item",{attrs:{index:"/teacher_course_learning_progress"}},[e._v("课程情况")])],1)],2)],1)],1),t("el-main",[t("el-table",{staticStyle:{width:"100%"},attrs:{data:e.courses}},[t("el-table-column",{attrs:{prop:"courseID",label:"课程ID",width:"180"}}),t("el-table-column",{attrs:{prop:"courseName",label:"课程名",width:"180"}}),t("el-table-column",{attrs:{prop:"description",label:"课程介绍"}}),t("el-table-column",{attrs:{prop:"maxStudents",label:"可报名学生人数"}}),t("el-table-column",{attrs:{label:"操作",width:"180"},scopedSlots:e._u([{key:"default",fn:function(a){return[t("el-button",{attrs:{size:"mini"},on:{click:function(t){return e.coursePage(a.row)}}},[e._v("课程学习情况")])]}}])})],1)],1)],1)],1)],1)},l=[],s=(a(4114),a(8355)),n={data(){return{courses:[]}},methods:{coursePage(e){this.$router.push({path:"/course_learning_progress",query:e})}},mounted(){s.A.get("http://localhost:8080/SongGuo_Learning/teacher/selectCourses?id="+localStorage.getItem("id"),{headers:{Token:localStorage.getItem("token")}}).then((e=>{"TOKEN_IS_NULL"==e.data.message?this.$router.push("/studentlogin"):(console.log(e.data),this.courses=e.data.data)}))}},o=n,u=a(1656),i=(0,u.A)(o,r,l,!1,null,null,null),c=i.exports}}]);
//# sourceMappingURL=180.4205a48d.js.map