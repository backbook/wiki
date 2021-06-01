<template>
  <a-layout>
    <a-layout-content :style="{background: '#fff', padding: '24px',margin: 0,minHeight: '280px' }">
      <p>
        <a-form layout="inline" :model="param">

          <a-form-item>
            <a-input v-model:value="param.name"  placeholder="名称">
            </a-input>
          </a-form-item>
          <a-form-item>
            <a-button type="primary" @click="handleQuery({page: 1,size: pagination.pageSize})">
              查询
            </a-button>
          </a-form-item>
          <a-form-item>
            <a-button type="primary" @click="add()">
              新增
            </a-button>
          </a-form-item>
        </a-form>

      </p>
      <a-table
        :columns="columns"
        :row-key="record => record.id"
        :data-source="categorys"
        :pagination="pagination"
        :loading="Loading"
        @change="handleTableChange"
        >
        <template #cover="{ text: cover }">
          <img v-if="cover" :width="40" :height="40" :src="cover" alt="avatar" />
        </template>
        <template v-slot:action="{text, record}">
          <a-space size="small">
            <a-button type="primary" @click="edit(record)">
              编辑
            </a-button>
            <a-popconfirm
                title="删除后不可恢复，确认删除?"
                ok-text="是"
                cancel-text="否"
                @confirm="handleDelete(record.id)"
            >
              <a-button type="danger">
                删除
              </a-button>
            </a-popconfirm>

          </a-space>
        </template>
      </a-table>
    </a-layout-content>
  </a-layout>
  <a-modal
      title="分类表单"
      v-model:visible="modalVisible"
      :confirm-loading="modalLoading"
      @ok="handleModalOk">

    <a-form :model="category" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
      <a-form-item label="名称">
        <a-input v-model:value="category.name" />
      </a-form-item>
      <a-form-item label="父分类">
        <a-input v-model:value="category.parent"/>
      </a-form-item>
      <a-form-item label="顺序">
          <a-input v-model:value="category.sort"/>
      </a-form-item>
    </a-form>
  </a-modal>

</template>

<script lang="ts">
  import {defineComponent, onMounted, ref} from "vue";
  import {message} from 'ant-design-vue'
  import axios from "axios";
  import {Tool} from "@/util/tool";
  export default defineComponent({
    name: 'AdminCategory',
    setup(){
      const param = ref();
      param.value = {};
      const categorys = ref();
      const pagination = ref({
        current: 1,
        pageSize: 10,
        total: 0
      });
      const loading = ref(false);

      //table的名称和python的dataframe相似
      const columns = [
        {
          title: '名称',
          dataIndex: 'name'
        }
        ,{
          title: '父分类',
          key: 'parent',
          dataIndex: 'parent'
        }
        ,{
          title: '顺序',
          dataIndex: 'sort'
        },
        {
          title: 'Action',
          key: 'action',
          slots: { customRender:  'action'}
        }
      ];

      /**
       * 数据查询
       */
      const handleQuery = (p:  Record<string, number>) =>{
        loading.value = true;
        axios.get("/category/list",{
          params: {
            page: p.page,
            size: p.size,
            name: param.value.name
          }
        }).then((response) =>{
          loading.value = false;
          const data = response.data;
          if(data.success){
            categorys.value = data.content.list;
            //重置分页按钮
            pagination.value.current = p.page;
            pagination.value.total = data.content.total;
          }else {
            message.error(data.message);
          }

        });
      };

      /**
       * 表格点击页码时触发
       */
      const handleTableChange = (pagination: Record<string, number>) => {
        console.log("分页自带的参数："+pagination)
        handleQuery({
          page: pagination.current,
          size: pagination.pageSize
        });
      };




      // ---- 表单 ------
      const category = ref({});
      const modalVisible = ref<boolean>(false);
      const modalLoading = ref<boolean>(false);
      const handleModalOk = () => {
        modalLoading.value = true;
        axios.post("/category/save",category.value).then((response) =>{
          const data = response.data; //data = commonResp
          modalLoading.value = false;
          if (data.success){
            modalVisible.value = false;
            //重新加载列表
            handleQuery({
              page: pagination.value.current,
              size: pagination.value.pageSize
            });
          }else {
            message.error(data.message);
          }

        });
      };


      /**
       * 编辑
       */
      const edit = (record:any) => {
        modalVisible.value = true;
        category.value = Tool.copy(record)
      };

      /**
       * 新增
       */
      const add = () => {
        modalVisible.value = true;
        category.value = {}
      };

      /**
       * handleDelete
       */
      const handleDelete = (id: string) => {
        modalLoading.value = true;
        axios.delete("/category/delete/"+id).then((response) =>{
          const data = response.data; //data = commonResp
          if (data.success){
            //重新加载列表
            handleQuery({
              page: pagination.value.current,
              size: pagination.value.pageSize
            });
          }
        });
      };

      /**
       * 初始化触发
       */
      onMounted(()=>{
        handleQuery({
          page: 1,
          size: pagination.value.pageSize
        });
      });

      return {
        param,
        categorys,
        pagination,
        columns,
        loading,
        handleTableChange,
        handleQuery,

        edit,
        add,
        handleDelete,

        category,
        modalVisible,
        modalLoading,
        handleModalOk
      }
    }
  });


</script>

