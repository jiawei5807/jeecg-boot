<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="图书名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="bookName">
              <a-input v-model="model.bookName" placeholder="请输入图书名称"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="图书作者" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="bookAuthor">
              <a-input v-model="model.bookAuthor" placeholder="请输入图书作者"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="出版社" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="bookPress">
              <a-input v-model="model.bookPress" placeholder="请输入出版社"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="图书定价" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="bookPrice">
              <a-input-number v-model="model.bookPrice" placeholder="请输入图书定价" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="图书库存" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="bookStock">
              <a-input-number v-model="model.bookStock" placeholder="请输入图书库存" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="图书信息" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="bookInfo">
              <j-editor v-model="model.bookInfo" />
            </a-form-model-item>
          </a-col>
        </a-row>
      </a-form-model>
    </j-form-container>
  </a-spin>
</template>

<script>

  import { httpAction, getAction } from '@/api/manage'
  import { validateDuplicateValue } from '@/utils/util'

  export default {
    name: 'BookForm',
    components: {
    },
    props: {
      //表单禁用
      disabled: {
        type: Boolean,
        default: false,
        required: false
      }
    },
    data () {
      return {
        model:{
         },
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
           bookName: [
              { required: true, message: '请输入图书名称!'},
           ],
           bookAuthor: [
              { required: true, message: '请输入图书作者!'},
           ],
           bookPress: [
              { required: true, message: '请输入出版社!'},
           ],
           bookPrice: [
              { required: true, message: '请输入图书定价!'},
              { pattern: /^(([1-9][0-9]*)|([0]\.\d{0,2}|[1-9][0-9]*\.\d{0,2}))$/, message: '请输入正确的金额!'},
           ],
           bookStock: [
              { required: true, message: '请输入图书库存!'},
              { pattern: /^-?\d+$/, message: '请输入整数!'},
           ],
           bookInfo: [
              { required: true, message: '请输入图书信息!'},
           ],
        },
        url: {
          add: "/book/add",
          edit: "/book/edit",
          queryById: "/book/queryById"
        }
      }
    },
    computed: {
      formDisabled(){
        return this.disabled
      },
    },
    created () {
       //备份model原始值
      this.modelDefault = JSON.parse(JSON.stringify(this.model));
    },
    methods: {
      add () {
        this.edit(this.modelDefault);
      },
      edit (record) {
        this.model = Object.assign({}, record);
        this.visible = true;
      },
      submitForm () {
        const that = this;
        // 触发表单验证
        this.$refs.form.validate(valid => {
          if (valid) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            httpAction(httpurl,this.model,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
            })
          }
         
        })
      },
    }
  }
</script>