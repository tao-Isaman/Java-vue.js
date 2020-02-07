
<template>
<div>
<v-card
    class="mx-auto"
    max-width="1200"
    outlined
    raised
    shaped
  >
  <v-container class = "back">
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="font">ระบบประเมินอาการ</h1>
      </v-flex>
    </v-layout>

   <v-row justify="center">
        <v-col cols="4">
          <v-form v-model="valid" ref="form">
            <v-row justify="center">
              <v-col cols="10">
                <v-text-field
                  outlined
                  label="Patient ID"
                  v-model="examinationSystem.Patient_ID"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-text-field>
                <p v-if="patientCheck != ''">Patient Name : {{patientName}}</p>
              </v-col>
              <v-col cols="2">
                <div class="my-2">
                  <v-btn @click="findPatient" depressed large color="primary">ค้นหา</v-btn>
                  <v-btn @click="show" depressed large color="yellow">แสดงผล</v-btn>
                </div>
              </v-col>
            </v-row>

            <div v-if="patientCheck">
              <v-text-field
                  outlined
                  label="ชีพจร(60วินาที)"
                  v-model="examinationSystem.pulse"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
              ></v-text-field>

              <v-text-field
                  outlined
                  label="ความดันโลหิต Systolic"
                  v-model="examinationSystem.pressureh"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
              ></v-text-field>

              <v-text-field
                  outlined
                  label="ความดันโลหิต Diastolic"
                  v-model="examinationSystem.pressurel"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
              ></v-text-field>

              <v-text-field
                  outlined
                  label="อาการ"
                  v-model="examinationSystem.symptom"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
              ></v-text-field>

              <v-row>
                <v-col cols="10">
                  <v-select
                    label="ช่วงเวลาในการประเมิน"
                    outlined
                    v-model="examinationSystem.Duration_ID"
                    :items="durations"
                    item-text="name"
                    item-value="id"
                    :rules="[(v) => !!v || 'Item is required']"
                    required
                  ></v-select>
                </v-col>
              </v-row>

              <v-row>
                <v-col cols="10">
                  <v-select
                    label="อาการหลังเข้ารับการรักษา"
                    outlined
                    v-model="examinationSystem.State_ID"
                    :items="states"
                    item-text="name"
                    item-value="id"
                    :rules="[(v) => !!v || 'Item is required']"
                    required
                  ></v-select>
                </v-col>
              </v-row>

              <v-row>
                <v-col cols="10">
                  <v-select
                    label="แพทย์เจ้าของไข้"
                    outlined
                    v-model="examinationSystem.Doctor_ID"
                    :items="doctors"
                    item-text="dname"
                    item-value="id"
                    :rules="[(v) => !!v || 'Item is required']"
                    required
                  ></v-select>
                </v-col>
              </v-row>

              <br>
              <br>

              <v-row justify="center">
                <v-col cols="12">
                  <v-btn @click="saveExaminationSystem" :class="{ red: !valid, green: valid }">submit</v-btn>
                  <v-btn style="margin-left: 15px;" @click="clear">clear</v-btn>
                </v-col>
              </v-row>
              <br />
            </div>
          </v-form>
        </v-col>
      </v-row>


  </v-container>
</v-card>
</div>
</template>

<script>
import http from "../http-common";

export default {
  name: "examinationSystem",
  data() {
    return {
      examinationSystem: {
        Patient_ID: "",
        Duration_ID: "",
        State_ID: "",
        Doctor_ID: "",
        pulse: "",
        pressureh: "",
        pressurel: "",
        symptom: "",
      },
      valid: false,
      patientCheck: false,
      patientName: ""
    };
  },
  methods: {
    /* eslint-disable no-console */

    // ดึงข้อมูล Employee ใส่ combobox
    getDurations() {
      http
        .get("/duration")
        .then(response => {
          this.durations = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล Video ใส่ combobox
    getStates() {
      http
        .get("/state")
        .then(response => {
          this.states = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล RentalType ใส่ combobox
    getDoctors() {
      http
        .get("/doctor")
        .then(response => {
          this.doctors = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // function ค้นหาลูกค้าด้วย ID
    findPatient() {
      http
        .get("/patient/" + this.examinationSystem.Patient_ID)
        .then(response => {
          console.log(response);
          if (response.data != null) {
            this.patientName = response.data.name;
            this.patientCheck = response.status;
          } else {
            this.clear()
          }
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },
    // function เมื่อกดปุ่ม submit
    saveExaminationSystem() {
      http
        .post(
          "/examinationSystem/" +
            this.examinationSystem.Patient_ID +
            "/" +
            this.examinationSystem.pulse +
            "/" +
            this.examinationSystem.pressureh +
            "/" +
            this.examinationSystem.pressurel +
            "/" +
            this.examinationSystem.symptom +
            "/" +
            this.examinationSystem.Duration_ID +
            "/" +
            this.examinationSystem.State_ID +
            "/" +
            this.examinationSystem.Doctor_ID,
          this.examinationSystem
        )
        .then(response => {
         console.log(response);
         const options1 = { title: "Alert", size: "sm" };
         this.$dialogs.alert("บันทึกข้อมูลสำเร็จ", options1);
         this.clear();
        })
        .catch(e => {
          console.log(e);
          const options2 = { title: "Alert", size: "sm" };
          this.$dialogs.alert("บันทึกข้อมูลไม่สำเร็จ", options2);
        });
      this.submitted = true;
    },
    show(){
       this.$router.push("/showEx");
    },
    clear() {
      this.$refs.form.reset();
      this.patientCheck = false;
    },
    refreshList() {
      this.getDurations();
      this.getStates();
      this.getDoctors();
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.getDurations();
    this.getStates();
    this.getDoctors();
  }
};
</script>

<style>
.font {
  font-family: 'Prompt', sans-serif;
  font-size: 30px ;
  color: black;
}
 .back {
 background-image: linear-gradient(0deg, rgba(255, 0, 150, 0.3), rgba(255, 0, 150, 0.3)), url(../assets/g1.png);
  background-size: cover;
  background-blend-mode: multiply;
}

</style>>
