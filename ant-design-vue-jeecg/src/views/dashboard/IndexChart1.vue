<template>
  <bar title="柱状图" :dataSource="dataSource" :height="420"/>
</template>

<script>
    import Bar from '@/components/chart/Bar'
    import { getAction, deleteAction, putAction, postAction, httpAction } from '@/api/manage'
    export default {
        name:"ChartDemo",
        components:{
            Bar
        },
        data(){
            return{
                dataSource: [
                ],
            }
        },
        created(){
            getAction('/book/list',null)
            .then(((res)=>{
               if(res.success){
                this.fill(res.result.records)
              }else{
                that.$message.warning(res.message);
              }
            }))
        },
        methods:{
            fill(data){
                this.dataSource = []
                data.forEach(temp => {
                    this.dataSource.push({"x":temp.bookName,"y":temp.bookStock})
                });
                console.log(this.dataSource)
            }
        }
    }
</script>

<style>

</style>