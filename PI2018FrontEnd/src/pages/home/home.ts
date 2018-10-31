import { Component } from '@angular/core';
import { IonicPage, NavController} from 'ionic-angular';
import { EnderecoPage } from '../endereco/endereco';
import { PessoaDTO } from '../../models/pessoa.dto';
import { FormBuilder, Validators } from '@angular/forms';
/**
 * Generated class for the HomePage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-home',
  templateUrl: 'home.html',
})
export class HomePage {
  public loginForm: any;
  messageEmail = ""
  messagePassword = "";
  errorEmail = false;
  errorPassword = false;
  formBuilder: any;

  constructor(formBuilder: FormBuilder) {
    this.loginForm = formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.compose([Validators.minLength(6), Validators.maxLength(20),
      Validators.required])],
    });
  }
  login() {
    let { email, password } = this.loginForm.controls;
 
    if (!this.loginForm.valid) {
      if (!email.valid) {
        this.errorEmail = true;
        this.messageEmail = "Ops! Email inválido !";
      } else {
        this.messageEmail = "";
      }
 
      if (!password.valid) {
        this.errorPassword = true;
        this.messagePassword ="A senha precisa ter de 6 a 20 caracteres"
      } else {
        this.messagePassword = "";
      }
    }
    else {
      alert("Login Realizado");
    }
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad HomePage');
  }
  
  irCadastro(){
   document.getElementById("login").style.display="none";
   document.getElementById("cadastro").style.display="block";
  }
  irLogin(){
    document.getElementById("cadastro").style.display="none";
    document.getElementById("login").style.display="block";
   }
   pessoa : PessoaDTO={
    nome : "", 
    cpf : "",
    email : "",
    telefone : "",
    endereco : null
  }
   avancar(){
     this.formBuilder.push(EnderecoPage,{
      nome :  this.pessoa.nome, 
      cpf :  this.pessoa.cpf,
      email :  this.pessoa.email,
      telefone :  this.pessoa.telefone
     });
   }
}
