<template>
  <div id="app">
    <div class='calculator-grid'>
      <p class='display' style="text-align:left;">Expression: {{current_expression}}</p>
      <p class='display'>{{current_display}}</p>
      <button @click="httprequest_special('percent')" class='grey-button'>%</button>
      <button @click="clear()" class='grey-button'>CE</button>
      <button @click="clear_whole_screen()" class='grey-button'>C</button>
      <button @click="delete_nums()" class='grey-button'>del</button>
      <button @click="httprequest_special('one_over_x')" class='grey-button'>1/x</button>
      <button @click="httprequest_special('square')" class='grey-button'>x^2</button>
      <button @click="httprequest_special('root')" class='grey-button'>sqrt(x)</button>
      <button @click="perform_operation(' / ', 'divide')" class='grey-button'>/</button>
      <button @click="append_nums('7')" class='button'>7</button>
      <button @click="append_nums('8')" class='button'>8</button>
      <button @click="append_nums('9')" class='button'>9</button>
      <button @click="perform_operation(' × ', 'multiply')" class='grey-button'>x</button>
      <button @click="append_nums('4')" class='button'>4</button>
      <button @click="append_nums('5')" class='button'>5</button>
      <button @click="append_nums('6')" class='button'>6</button>
      <button @click="perform_operation(' - ', 'subtract')" class='grey-button'>-</button>
      <button @click="append_nums('1')" class='button'>1</button>
      <button @click="append_nums('2')" class='button'>2</button>
      <button @click="append_nums('3')" class='button'>3</button>
      <button @click="perform_operation(' + ', 'add')" class='grey-button'>+</button>
      <button @click="add_sign()" class='button'>+/-</button>
      <button @click="append_nums('0')" class='button'>0</button>
      <button @click="dec_point()" class='button'>.</button>
      <button @click="result()" class='grey-button' style="background-color: rgb(72, 115, 231);">=</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'App',
  components: {

  },
  data() {
    return{
      current_display : '0',
      current_expression: '',
      input1 : '',
      current_result : '',
      operation: ''
    }
  },
  methods: {
    clear(){
      this.current_display = '0';
    },
    delete_nums(){
      this.current_display = this.current_display.substring(0, this.current_display.length-1);
      if(this.current_display.length === 0){
        this.current_display = '0';
      }
    },
    append_nums(clicked){
      if(this.current_display === '0'){
        this.current_display = clicked;
      }
      else{
        if(this.current_display === this.operator || this.current_display === 'E') this.current_display = '';
        this.current_display += clicked;
      }
    },
    dec_point(){
      if(!this.current_display.includes('.')){
        this.current_display += '.'; 
      }
    },
    add_sign(){
      if(this.current_display === '0' || this.current_display === 'E'){
        this.current_display = '-';
      }else if(this.current_display === '-'){
        this.current_display = '0';
      }else if(this.current_display.indexOf('-') === 0){
        this.current_display = this.current_display.substr(1,);
      }else{
        this.current_display = '-' + this.current_display;
      }
    },
    clear_whole_screen(){
      this.current_display = '0';
      this.current_expression = '';
      this.input1 = '';
    },
    result(){
      if(this.input1 !== null){
        fetch('http://localhost:8085/'+ this.operation +'?input1='+this.input1+'&input2='+this.current_display)
        .then(response => {
        return response.text();
        }).then(data => {
        this.current_expression = '';
        this.input1 = data;
        this.current_display = data;
        this.operation = '';
        })
      }
    },
    perform_operation(operator, operation){
      if(this.input1.length != 0){
        this.httprequest(this.operation, operator);
      }
      if(this.current_display === 'E' || this.current_display === '-') this.current_display = '0';
      this.input1 = this.current_display;
      this.current_expression += this.current_display;
      this.current_expression += operator;
      this.current_display= '0';
      this.operation = operation;
    },
    httprequest(operation, operator){
        fetch('http://localhost:8085/'+ operation +'?input1='+this.input1+'&input2='+this.current_display)
        .then(response => {
        return response.text();
        }).then(data => {
        if(data === 'E'){
          this.current_display = 'E';
          this.current_expression = '';
          this.input1 = '';
        }else{
          this.current_expression = data + operator;
          this.input1 = data;
        }
        })
    },
    httprequest_special(special_op){
        fetch('http://localhost:8085/'+ special_op +'?input1='+this.current_display)
        .then(response => {
        return response.text();
        }).then(data => {
        this.current_display = data;
        }) 
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.display{
  grid-column: 1/5;
  font-size: 1.2rem;
}
.calculator-grid{
  border: 3px solid black;
  margin: 200px 550px;
  display: grid;
  grid-auto-rows: minmax(30px, auto);
  grid-template-columns: repeat(4, 1fr);
  width: 360px;
  height: 400px;
}
.grey-button{
  background-color: #cfdadf;
  font-size: 1.1rem;
  border: 2px solid rgb(184, 184, 184);
  cursor:pointer;
}
.button{
  border: 2px solid rgb(184, 184, 184);
  font-size: 1.1rem;
  cursor:pointer;
}
.button:active, .grey-button:active {
  transform: scale(0.97);
}
</style>
