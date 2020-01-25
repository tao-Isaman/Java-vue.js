<template>
    <v-app>
    <v-container style="height: 200%; margin-right:5%">
          <v-card style="width:90%; margin:auto; background-color:#FFFFFF">
            <v-layout text-center wrap>
              <v-flex mb-4>
                <br />
                <h1 class="display-1 font-weight-bold mb-3">แสดงผลประวัติการประเมินอาการ</h1>
              </v-flex>
            </v-layout>
            <v-row justify="center">
              <v-col cols="30">
                <v-container></v-container>
                <v-container></v-container>

                <v-data-table :headers="headers" :items="items" :items-per-page="5"></v-data-table>

              </v-col>
            </v-row>
          </v-card>
        </v-container>
    </v-app>
</template>
<script>
import http from "../http-common";
export default {
  name: "showEx",
  data() {
    return {
      headers: [
        { text: "วันที่", value: "checkDate" },
        { text: "PatientID", value: "patient.nationalID" },
        { text: "DoctorID", value: "checkBy.id" },
        { text: "ชีพจร", value: "pulse" },
        { text: "ความดัน", value: "pressure" },
        { text: "อาการ", value: "symptom" },
        { text: "ช่วงเวลา", value: "duration.name" },
        { text: "สถานะอาการ", value: "state.name" }
      ],
      items: []
    };
  },
  methods: {
      /* eslint-disable no-console */
      // ดึงข้อมูล ExaminationSystem ใส่ combobox
      getExaminationSystems() {
        http
          .get("/examinationSystem")
          .then(response => {
            this.items = response.data;
            console.log(this.items);
          })
          .catch(e => {
            console.log(e);
          });
      }
      /* eslint-disable no-console */
   },
   mounted() {
       this.getExaminationSystems();
     }
   };
</script>