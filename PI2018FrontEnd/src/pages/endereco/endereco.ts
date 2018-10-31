import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams} from 'ionic-angular';
import { PessoaDTO } from '../../models/pessoa.dto';
import { Camera, CameraOptions } from '@ionic-native/camera';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';
import { EnderecoJsonViaCepDTO } from '../../models/enderecoJsonViaCep.dto';



@IonicPage()
@Component({
  selector: 'page-endereco',
  templateUrl: 'endereco.html',
})
export class EnderecoPage {
  foto: string;
 

  
  

  constructor(public navCtrl: NavController,public navParams: NavParams, private camera:Camera,public http : HttpClient) { 
  }

  pessoa : PessoaDTO={
    nome : this.navParams.get('nome'), 
    cpf : this.navParams.get('cpf'),
    email : this.navParams.get('email'),
    telefone : this.navParams.get('telefone'),
    endereco : {
      cep : "", 
      rua : "",
      bairro : "",
      cidade : "",
      estado : "",
      pais : ""
    }
  }
  json : EnderecoJsonViaCepDTO ={
    cep : "", 
    logradouro : "",
    complemento : "",
    bairro : "",
    localidade : "",
    uf : "",
    unidade : "",
    ibge : "",
    gia : ""
  
  }
  buscaCep(){
    var cep = this.pessoa.endereco.cep;
    let data: Observable<any>;
    data = this.http.get(`http://viacep.com.br/ws/${cep}/json/`);
    data.subscribe(result =>{
      this.json = result;
      this.pessoa.endereco.bairro = this.json.bairro;
      this.pessoa.endereco.rua = this.json.logradouro;
      this.pessoa.endereco.cidade = this.json.localidade;
      this.pessoa.endereco.estado = this.json.uf;
      this.pessoa.endereco.pais ="Brasil";
    });
  } 
  detect(){
    
    
  } 
  tirarFoto(){
    this.foto=''; 

    const options: CameraOptions={
      quality:100,
      destinationType: this.camera.DestinationType.DATA_URL,
      encodingType: this.camera.EncodingType.JPEG,
      mediaType: this.camera.MediaType.PICTURE,
      targetWidth:100,
      targetHeight:100

    }
    this.camera.getPicture(options).then((ImageData) =>{
          let base64image = 'data:image/jpeg;base64,'+ImageData;
          this.foto = base64image;
          let data: Observable<any>;
          data = this.http.post(`http://10.116.208.184:8080/pessoa/binario`,base64image);
          data.subscribe(result =>{
            console.log(result);
          });
      
    },(error)=>{
      console.error(error);
    })
    .catch((error) =>{
      console.error(error);
    })
  }

}
