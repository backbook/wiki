<template>
  <a-layout>
    <a-layout-sider width="200" style="background: #fff">
      <a-menu
          mode="inline"
          v-model:selectedKeys="selectedKeys2"
          v-model:openKeys="openKeys"
          :style="{ height: '100%', borderRight: 0 }"
          @click="handleClick"
      >
        <a-menu-item key="welcome">
            <MailOutlined/>
            <span>欢迎</span>
        </a-menu-item>
        <a-sub-menu v-for="item in level1" :key="item.id">
          <template v-slot:title>
            <span><user-outlined />{{item.name}}</span>
          </template>
          <a-menu-item v-for="child in item.children" :key="child.id">
            <MailOutlined/><span>{{child.name}}</span>
          </a-menu-item>
        </a-sub-menu>
      </a-menu>
    </a-layout-sider>

    <a-layout-content
        :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >

      <div class="welcome" v-show="isShowWelcome">
        <h1>欢迎使用</h1>
      </div>

      <a-list v-show="!isShowWelcome"
              item-layout="vertical" size="large"
              :grid="{ gutter:10, column:3}"
              :loading="Loading"
              :pagination="pagination"
              :data-source="ebooks"
              @change="handleTableChange">
        <template #renderItem="{ item }">
          <a-list-item key="item.name">
            <template #actions>
          <span v-for="{ type, text } in actions" :key="type">
            <component v-bind:is="type" style="margin-right: 8px" />
            {{text}}
          </span>
            </template>
            <a-list-item-meta :description="item.description">
              <template #title>
                <a :href="item.name">{{ item.name }}</a>
              </template>
              <template #avatar>
                <a-avatar :src="item.cover" />
              </template>
            </a-list-item-meta>
          </a-list-item>
        </template>
      </a-list>


    </a-layout-content>
  </a-layout>
</template>

<script lang="ts">
import { defineComponent,onMounted,ref } from 'vue';
import axios from 'axios'
import { message } from 'ant-design-vue'
import {Tool} from '@/util/tool'


export default defineComponent({
  name: 'Home',
  setup(){
    const ebooks = ref();
    const loading = ref(false);

    const level1 = ref();
    let categorys: any;
    const isShowWelcome = ref(true);
    /**
     * 查询所有分类
     */
    const handleQueryCategory = () => {
      axios.get("category/all").then((response) =>{
        const data = response.data
        if (data.success) {
          categorys = data.content;
          console.log("原始数组： ", categorys);

          level1.value = []
          level1.value = Tool.array2Tree(categorys,0)
          console.log("树形结构： ",level1.value)
        }else{
          message.error(data.message)
        }
      })
    }

    const handleClick = (value: any) => {
      // console.log("menu click "+value.key)
      isShowWelcome.value = value.key === "welcome";
    }


    const pagination = ref({
      current: 1,
      pageSize: 1000,
      total: 0
    });


    const handleQuery = (p:  Record<string, number>) =>{
      loading.value = true;
      axios.get("/ebook/list",{
        params: {
          page: p.page,
          size: p.size
        }
      }).then((response) =>{
        loading.value = false;
        const data = response.data;
        ebooks.value = data.content.list;
        //重置分页按钮
        pagination.value.current = p.page;
        pagination.value.total = data.content.total;
      });
    }

    const handleTableChange = (pagination: Record<string, number>) => {
      handleQuery({
        page: pagination.current,
        size: pagination.pageSize
      });
    };


    const actions: Record<string, string>[] = [
      { type: 'StarOutlined', text: '156' },
      { type: 'LikeOutlined', text: '156' },
      { type: 'MessageOutlined', text: '2' },
    ];


    /**
     * 初始化触发
     */
    onMounted(()=>{
      handleQueryCategory();
      handleQuery(
          {
            page: 1,
            size: pagination.value.pageSize
          }
      )
    });

    return{
      ebooks,
      pagination,
      actions,
      loading,
      isShowWelcome,
      handleTableChange,
      handleClick,
      level1
    }
  }
});
</script>

<style scoped>
.ant-avatar{
  width: 50px;
  height: 50px;
  line-height: 50px;
  border-radius: 8%;
  margin: 5px 0;
}
</style>

