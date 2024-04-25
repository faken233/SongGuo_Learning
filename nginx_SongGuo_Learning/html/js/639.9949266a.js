"use strict";(self["webpackChunkworkspace"]=self["webpackChunkworkspace"]||[]).push([[639],{4639:function(e,t,o){o.r(t),o.d(t,{default:function(){return M}});var s=function(){var e=this,t=e._self._c;return t("div",[t("el-page-header",{attrs:{content:"课程编辑界面"},on:{back:e.goBack}}),t("el-descriptions",{attrs:{title:"课程信息"}},[t("el-descriptions-item",{attrs:{label:"课程ID"}},[e._v(e._s(this.course.courseID))]),t("el-descriptions-item",{attrs:{label:"课程名字"}},[e._v(e._s(this.course.courseName))]),t("el-descriptions-item",{attrs:{label:"课程描述"}},[e._v(e._s(this.course.description))])],1),t("el-button",{on:{click:function(t){e.dialogFormVisible=!0}}},[e._v("增加新课程章节")]),t("el-dialog",{attrs:{title:"增加课程章节",visible:e.dialogFormVisible},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[t("el-form",{attrs:{model:e.newChapter}},[t("el-form-item",{attrs:{label:"章节名字"}},[t("el-input",{attrs:{autocomplete:"off"},model:{value:e.newChapter.chapterName,callback:function(t){e.$set(e.newChapter,"chapterName",t)},expression:"newChapter.chapterName"}})],1),t("el-form-item",{attrs:{label:"章节内容"}},[t("el-input",{attrs:{type:"textarea",placeholder:"请输入章节内容"},model:{value:e.newChapter.content,callback:function(t){e.$set(e.newChapter,"content",t)},expression:"newChapter.content"}})],1)],1),t("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{on:{click:function(t){e.dialogFormVisible=!1}}},[e._v("取 消")]),t("el-button",{attrs:{type:"primary"},on:{click:e.submitForm}},[e._v("确 定")])],1)],1),t("br"),e._l(e.chapters,(function(o,s){return t("div",{key:s,staticClass:"containerOfComponents"},[e._v(" 章节名: "+e._s(o.chapterName)+" 章节ID: "+e._s(o.chapterID)+" "),t("el-button",{staticClass:"deleteButton",attrs:{type:"text"},on:{click:function(t){return e.deleteChapter(o.chapterID)}}},[e._v("删除章节")]),t("el-switch",{attrs:{"active-color":"#13ce66","inactive-color":"#ff4949"},model:{value:e.componentVisibility[s],callback:function(t){e.$set(e.componentVisibility,s,t)},expression:"componentVisibility[index]"}}),e.componentVisibility[s]?t("Show_chapter_info",{attrs:{chapter:o}}):e._e()],1)}))],2)},n=[],a=(o(4114),o(8355)),i=function(){var e=this,t=e._self._c;return t("div",[t("el-card",{staticClass:"box-card"},[t("div",[e._v(" "+e._s(this.chapter.content)+" ")])]),t("el-button",{attrs:{type:"primary"},on:{click:function(t){e.addTFQuestionDialogVisible=!0}}},[e._v("添加一道判断题")]),t("el-dialog",{attrs:{title:"添加一道判断题",visible:e.addTFQuestionDialogVisible},on:{"update:visible":function(t){e.addTFQuestionDialogVisible=t}}},[t("el-form",{attrs:{model:e.newTFQuestion}},[t("el-form-item",{attrs:{label:"输入题目内容"}},[t("el-input",{attrs:{autocomplete:"off"},model:{value:e.newTFQuestion.content,callback:function(t){e.$set(e.newTFQuestion,"content",t)},expression:"newTFQuestion.content"}})],1),t("el-form-item",{attrs:{label:"选择答案"}},[t("el-select",{model:{value:e.newTFQuestion.answer,callback:function(t){e.$set(e.newTFQuestion,"answer",t)},expression:"newTFQuestion.answer"}},[t("el-option",{attrs:{label:"对",value:"true"}}),t("el-option",{attrs:{label:"错",value:"false"}})],1)],1)],1),t("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{on:{click:function(t){e.addTFQuestionDialogVisible=!1}}},[e._v("取 消")]),t("el-button",{attrs:{type:"primary"},on:{click:e.addTFQuestion}},[e._v("确 定")])],1)],1),t("el-button",{attrs:{type:"primary"},on:{click:function(t){e.addMCQuestionDialogVisible=!0}}},[e._v("添加一道选择题")]),t("el-dialog",{attrs:{title:"添加一道选择题",visible:e.addMCQuestionDialogVisible},on:{"update:visible":function(t){e.addMCQuestionDialogVisible=t}}},[t("el-form",{attrs:{model:e.newMCQuestion}},[t("el-form-item",{attrs:{label:"输入题目内容"}},[t("el-input",{attrs:{autocomplete:"off"},model:{value:e.newMCQuestion.content,callback:function(t){e.$set(e.newMCQuestion,"content",t)},expression:"newMCQuestion.content"}})],1),t("el-form-item",{attrs:{label:"输入各个选项 例: A.1 B.2 C.3"}},[t("el-input",{attrs:{autocomplete:"off"},model:{value:e.newMCQuestion.options,callback:function(t){e.$set(e.newMCQuestion,"options",t)},expression:"newMCQuestion.options"}})],1),t("el-form-item",{attrs:{label:"输入答案索引"}},[t("el-input",{attrs:{autocomplete:"off"},model:{value:e.newMCQuestion.answer,callback:function(t){e.$set(e.newMCQuestion,"answer",t)},expression:"newMCQuestion.answer"}})],1)],1),t("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{on:{click:function(t){e.addMCQuestionDialogVisible=!1}}},[e._v("取 消")]),t("el-button",{attrs:{type:"primary"},on:{click:e.addMCQuestion}},[e._v("确 定")])],1)],1),t("el-button",{attrs:{type:"primary"},on:{click:function(t){e.addSAQuestionDialogVisible=!0}}},[e._v("添加一道简答题")]),t("el-dialog",{attrs:{title:"添加一道简答题",visible:e.addSAQuestionDialogVisible},on:{"update:visible":function(t){e.addSAQuestionDialogVisible=t}}},[t("el-form",{attrs:{model:e.newSAQuestion}},[t("el-form-item",{attrs:{label:"输入题目内容"}},[t("el-input",{attrs:{autocomplete:"off"},model:{value:e.newSAQuestion.content,callback:function(t){e.$set(e.newSAQuestion,"content",t)},expression:"newSAQuestion.content"}})],1),t("el-form-item",{attrs:{label:"输入参考答案"}},[t("el-input",{attrs:{type:"textarea",autocomplete:"off"},model:{value:e.newSAQuestion.answer,callback:function(t){e.$set(e.newSAQuestion,"answer",t)},expression:"newSAQuestion.answer"}})],1)],1),t("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{on:{click:function(t){e.addSAQuestionDialogVisible=!1}}},[e._v("取 消")]),t("el-button",{attrs:{type:"primary"},on:{click:e.addSAQuestion}},[e._v("确 定")])],1)],1),e._l(e.questions,(function(o,s){return t("div",{key:s,staticClass:"containerOfComponents"},[t(e.getQuestionComponent(o.type),{tag:"component",attrs:{question:o}}),t("br")],1)}))],2)},l=[],r=function(){var e=this,t=e._self._c;return t("div",[t("el-col",{attrs:{span:8}},[t("el-card",{attrs:{shadow:"hover"}},[t("div",{attrs:{slot:"header"},slot:"header"},[e._v("判断题")]),t("p",[e._v("内容:"+e._s(e.question.content))]),t("p",[e._v("答案:"+e._s(e.question.answer))]),t("el-button",{attrs:{type:"warning"},on:{click:function(t){return e.deleteQuestion(e.question.questionID)}}},[e._v("删除题目")])],1)],1),t("br")],1)},c=[],u={props:{question:Object},data(){return{}},methods:{deleteQuestion(e){this.$confirm("确认删除题目?").then((()=>{a.A.get("http://localhost:8080/SongGuo_Learning/teacher/deleteQuestion?questionID="+e,{headers:{Token:localStorage.getItem("token")}}).then((e=>{console.log(e.data),"DELETE_OK"==e.data.message&&this.$notify.success({title:"操作成功",message:"您已成功删除一道题目!"})}))})).catch((()=>{}))}}},d=u,h=o(1656),p=(0,h.A)(d,r,c,!1,null,null,null),m=p.exports,g=function(){var e=this,t=e._self._c;return t("div",[t("el-col",{attrs:{span:8}},[t("el-card",{attrs:{shadow:"hover"}},[t("div",{attrs:{slot:"header"},slot:"header"},[e._v("选择题")]),t("p",[e._v("内容:"+e._s(e.question.content))]),t("p",[e._v("选项:"+e._s(e.question.options))]),t("p",[e._v("答案:"+e._s(e.question.answer))]),t("el-button",{attrs:{type:"warning"},on:{click:function(t){return e.deleteQuestion(e.question.questionID)}}},[e._v("删除题目")])],1)],1),t("br")],1)},f=[],b={props:{question:Object},data(){return{selectedOption:null}},methods:{deleteQuestion(e){this.$confirm("确认删除题目?").then((()=>{a.A.get("http://localhost:8080/SongGuo_Learning/teacher/deleteQuestion?questionID="+e,{headers:{Token:localStorage.getItem("token")}}).then((e=>{console.log(e.data),"DELETE_OK"==e.data.message&&this.$notify.success({title:"操作成功",message:"您已成功删除一道题目!"})}))})).catch((()=>{}))}}},_=b,v=(0,h.A)(_,g,f,!1,null,null,null),Q=v.exports,w=function(){var e=this,t=e._self._c;return t("div",[t("el-col",{attrs:{span:8}},[t("el-card",{attrs:{shadow:"hover"}},[t("div",{attrs:{slot:"header"},slot:"header"},[e._v("简答题")]),t("p",[e._v("内容: "+e._s(e.question.content))]),t("p",[e._v("参考答案: "+e._s(e.question.answer))]),t("el-button",{attrs:{type:"warning"},on:{click:function(t){return e.deleteQuestion(e.question.questionID)}}},[e._v("删除题目")])],1)],1),t("br")],1)},C=[],k={props:{question:Object},data(){return{answer:""}},methods:{deleteQuestion(e){this.$confirm("确认删除题目?").then((()=>{a.A.get("http://localhost:8080/SongGuo_Learning/teacher/deleteQuestion?questionID="+e,{headers:{Token:localStorage.getItem("token")}}).then((e=>{console.log(e.data),"DELETE_OK"==e.data.message&&this.$notify.success({title:"操作成功",message:"您已成功删除一道题目!"})}))})).catch((()=>{}))}}},D=k,y=(0,h.A)(D,w,C,!1,null,null,null),S=y.exports,T={components:{TrueFalseQuestion:m,MultipleChoiceQuestion:Q,ShortAnswerQuestion:S},props:{chapter:Object},data(){return{addTFQuestionDialogVisible:!1,newTFQuestion:{chapterID:this.chapter.chapterID,type:5,content:"",answer:""},addMCQuestionDialogVisible:!1,newMCQuestion:{chapterID:this.chapter.chapterID,type:6,content:"",answer:"",options:""},addSAQuestionDialogVisible:!1,newSAQuestion:{chapterID:this.chapter.chapterID,type:7,content:"",answer:""},questions:[]}},methods:{addTFQuestion(){a.A.post("http://localhost:8080/SongGuo_Learning/teacher/addQuestionToChapter?questionType=5",this.newTFQuestion,{headers:{Token:localStorage.getItem("token")}}).then((e=>{console.log(e.data),"ADD_OK"==e.data.message&&(this.$notify.success({title:"添加成功",message:"您已成功添加一道判断题!"}),this.addTFQuestionDialogVisible=!1)}))},addMCQuestion(){a.A.post("http://localhost:8080/SongGuo_Learning/teacher/addQuestionToChapter?questionType=6",this.newMCQuestion,{headers:{Token:localStorage.getItem("token")}}).then((e=>{console.log(e.data),"ADD_OK"==e.data.message&&(this.$notify.success({title:"添加成功",message:"您已成功添加一道选择题!"}),this.addMCQuestionDialogVisible=!1)}))},addSAQuestion(){a.A.post("http://localhost:8080/SongGuo_Learning/teacher/addQuestionToChapter?questionType=7",this.newSAQuestion,{headers:{Token:localStorage.getItem("token")}}).then((e=>{console.log(e.data),"ADD_OK"==e.data.message&&(this.$notify.success({title:"添加成功",message:"您已成功添加一道简答题!"}),this.addSAQuestionDialogVisible=!1)}))},getQuestionComponent(e){switch(e){case 5:return"TrueFalseQuestion";case 6:return"MultipleChoiceQuestion";case 7:return"ShortAnswerQuestion";default:return"div"}}},mounted(){a.A.get("http://localhost:8080/SongGuo_Learning/teacher/getChapterQuestions?chapterID="+this.chapter.chapterID,{headers:{Token:localStorage.getItem("token")}}).then((e=>{console.log(e.data),this.questions=e.data.data,console.log(this.questions)}))}},A=T,I=(0,h.A)(A,i,l,!1,null,"709f456f",null),V=I.exports,q={components:{Show_chapter_info:V},data(){let e=this.$route.query,t=e.courseID;return{componentVisibility:[],dialogFormVisible:!1,course:e,courseID:t,chapters:[],newChapter:{courseID:t,chapterName:"",content:""}}},methods:{goBack(){this.$router.push("/teacher_homepage_personal_info")},deleteChapter(e){this.$confirm("确认删除章节?").then((()=>{a.A.get("http://localhost:8080/SongGuo_Learning/teacher/deleteChapter?chapterID="+e,{headers:{Token:localStorage.getItem("token")}}).then((e=>{console.log(e.data),"DELETE_OK"==e.data.message&&this.$notify.success({title:"操作成功",message:"您已成功删除一个章节!"})}))})).catch((()=>{}))},submitForm(){a.A.post("http://localhost:8080/SongGuo_Learning/teacher/addNewChapterToCourse",this.newChapter,{headers:{Token:localStorage.getItem("token")}}).then((e=>{console.log(e.data),"ADD_OK"==e.data.message&&(this.$notify.success({title:"添加章节成功",message:"您已成功添加一个课程章节!"}),this.dialogFormVisible=!1)}))}},mounted(){a.A.get("http://localhost:8080/SongGuo_Learning/teacher/selectCourseChaptersByCourseID?courseID="+this.courseID,{headers:{Token:localStorage.getItem("token")}}).then((e=>{"TOKEN_IS_NULL"!=e.data.message?(console.log(e.data),this.chapters=e.data.data):this.$router.push("/studentlogin")}))}},F=q,$=(0,h.A)(F,s,n,!1,null,"5ae83199",null),M=$.exports}}]);
//# sourceMappingURL=639.9949266a.js.map