<template>
  <div class="container">
    <div class="container-header">
      <h1>classes</h1>
      <span class="add">
        <button class="btn-add" v-on:click="showDialog">
          Add classes
        </button>
      </span>
    </div>
    <div class="container-body">
      <table class="table">
        <thead>
          <tr>
            <th>Id Classe</th>
<th>Nom</th>

          </tr>
        </thead>
        <tbody>
          <tr v-for="(classe, id) in classes" :key="id">
            <th>{{ classe.idClasse }}</th>
<th>{{ classe.nom }}</th>

            <th>
              <span class="action-container">
                <button class="btn edit" @click="editFramework(id)">Edit</button>
                <button class="btn delete">Delete</button>
              </span>
            </th>
          </tr>
        </tbody>
      </table>



    </div>

    <dialog id="dialog-classes"> <!-- Ovaina par rapport am anarany ilay tableschema -->
      <p>Add new classe</p>
      <div class="form-container">
        <form>
          <div class="input-group">
    <label for="nom">nom:</label>
    <input id="nom" type="text" name="nom" v-model="nom">
</div>
          <div class=" button-group">
            <button class="btn delete" @click.prevent="closeDialog">Cancel</button>
            <div>
              <button class="btn submit" v-if="!isToUpdate" @click.prevent="addFramework">Add</button>
              <button class="btn submit" v-else @click.prevent="updateForm">Update</button>
            </div>

          </div>
        </form>
      </div>
    </dialog>

  </div>
</template>

<style scoped>
.form-container {
  width: 400px;
  /* Adjust as needed */
  margin: 0 auto;
  /* Center the form horizontally */
}

/* Style the input groups */
.input-group {
  margin-bottom: 15px;
  /* Add spacing between input groups */
}

.container {
  width: 1000px;
  height: fit-content;
  /* box-shadow: 20px 20px 55px #bebebe,
    -20px -20px 55px #ffffff; */

   /*  padding-bottom: 50px;
    padding-left: 20px;
    border-radius: 10px;*/
} 

.button-group {
  width: 50%;
  display: flex;
  justify-content: space-around;
  margin-left: 50%;
}

label {
  font-weight: bold;
  display: inline-block;
  width: 100px;
  /* Adjust as needed */
  margin-right: 10px;
  /* Add spacing between label and input */
}

/* Style inputs */
input[type="text"],
input[type="number"],
input[type="date"] {
  padding: 8px;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 4px;
  width: calc(100% - 110px);
  /* Adjust as needed */
  box-sizing: border-box;
  display: inline-block;
}

/* Style submit button */
input[type="submit"] {
  padding: 10px 20px;

  font-size: 16px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  /* Add transition effect */

  margin-left: 310px;
}

/* Hover effect for submit button */
input[type="submit"]:hover {
  background-color: #0056b3;
}

.btn-add {
  border-radius: 8px;
  border: 1px solid transparent;
  padding: 0.6em 1.2em;
  font-size: 1em;
  font-weight: 500;
  font-family: inherit;
  cursor: pointer;
  transition: border-color 0.25s;
}

.btn-add:hover {
  transform: scale(1.1);
}

.btn-add:focus,
.btn-add:focus-visible {
  outline: 4px auto -webkit-focus-ring-color;
}

.table {
  width: 100%;

}

.table th {
  text-align: start;
}

.table thead tr {
  border-bottom: solid 1px #242424;
}

.action-container {
  width: 100%;
  display: flex;
  justify-content: space-around;
}

.table tbody th {
  font-weight: 400;
  font-size: 0.90rem;

}

.btn {
  border: none;
  width: 70px;
  height: 30px;
  border-radius: 2px;
  cursor: pointer;
}


dialog {
  border: none;
  border-radius: 10px;
}



.edit {
  background-color: #ffdc7d;
}

.edit:active {
  background-color: #debe68;
}

.delete {
  background-color: #f03a5f;
  color: white;
}

.delete:active {
  background-color: #d03251;
}

.submit {
  background-color: #007bff;
  color: white;
}

dialog p {
  margin-right: 20px;
  font-size: 30px;
  color: #0056b3;

}

.cancel {
  background-color: #f03a5f;
  color: white;
  border: none;
}
</style>

<script>
import axios from 'axios'
import 'primeicons/primeicons.css';

export default {
  
  data() {
    return { 
        classes: [],
        idClasse: '',
nom: '',

        isToUpdate: false,
        dialog: null
    }
  },

  methods: {
    
    async fetchItems() {
      const response = await axios.get('http://localhost:8080/classe-1.0-SNAPSHOT/classes.do')
      this.classes = response.data.classes
    },
    
    async updateForm() {

      const request = {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          idClasse: this.idClasse,
nom: this.nom,

        })
      }
      
      const response = await fetch(
        'http://localhost:8080/classe-1.0-SNAPSHOT/classes.do',
        request
      )
      const data = await response.json()
      this.classes.push(data)
    },

    showDialog () {
      this.dialog.showModal()
    },

    async addFramework() {

      const request = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          idClasse: this.idClasse,
nom: this.nom,

        })
      }
      const response = await fetch(
        'http://localhost:8080/classe-1.0-SNAPSHOT/classes.do',
        request
      )
      const data = await response.json()
      this.classes.push(data)
      this.resetField()
      // Close the dialog
      this.dialog.close();
    },

    resetField() {
      this.idClasse = ''
this.nom = ''

    },

    closeDialog() {
      this.resetField();
      this.isToUpdate = false;
      this.dialog.close();
    },

    editFramework(index) {
      this.isToUpdate = true;
      const item = this.classes[index];
      this.idClasse = item.idClasse
this.nom = item.nom

      this.showDialog(); 
    }
  },

  mounted() {
    this.dialog = document.getElementById("dialog-classes")
    this.fetchItems()
  }
}

</script>