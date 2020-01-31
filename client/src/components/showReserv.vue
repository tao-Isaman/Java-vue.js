<template>
    <v-app>
    <v-container style="height: 500%; margin-right:10%">
          <v-card style="width:100%; margin:auto; background-color:#FFFFFF">
            <v-layout text-center wrap>
              <v-flex mb-4>
                <br />
                <h1 class="display-1 font-weight-bold mb-3">รายการจองเตียง</h1>
              </v-flex>
            </v-layout>
            <v-row justify="center">
              <v-col cols="100">
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
  name: "showReserv",
  data() {
    return {
      headers: [
        { text: "วันที่", value: "reservDate" },
        { text: "หมายเลขเตียง", value: "number"},
        { text: "หมายเลขประจำตัวประชาชน", value: "patient.nationalID" },
        { text: "หมายหตุ", value: "note"},
        { text: "พยาบาล", value: "nurse.name" },
        { text: "แผนกผู้ป่วย", value: "department.departmentlSelect" },
      ],
      items: []
    };
  },
  methods: {
      /* eslint-disable no-console */
      // ดึงข้อมูล getReservation ใส่ combobox
      getReservation() {
        http
          .get("/reservation")
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
       this.getReservation();
     }
   };
</script>