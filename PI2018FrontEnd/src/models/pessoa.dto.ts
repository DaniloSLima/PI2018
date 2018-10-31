import { EnderecoDTO } from "./endereco.dto";

export interface PessoaDTO{
    nome : string; 
    cpf : string;
    email : string;
    telefone : string;
    endereco : EnderecoDTO;
}