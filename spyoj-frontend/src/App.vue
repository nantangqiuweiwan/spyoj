<template>
  <div id="app">
    <template v-if="route.path.startsWith('/user')">
      <router-view />
    </template>
    <template v-else>
      <cLayout />
    </template>
  </div>
</template>

<style>
#app {
}
</style>
<script setup lang="ts">
import cLayout from "@/layouts/BasicLayout.vue";

import { onMounted } from "vue";
import { useRoute } from "vue-router";

const route = useRoute();

/**
 * 全局初始化函数，在全局单次调用的代码，都可以写到这里
 */
const doInit = () => {
  console.log("hello welcome to spyoj");
};

onMounted(() => {
  doInit();
});
</script>
