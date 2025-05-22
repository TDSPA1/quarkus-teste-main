package Services;

import Entidades.Passagem;

public class PassagemService {
    public boolean validatePassagem(Passagem passagem) {
        if(passagem == null)
            return false;
        if(passagem.getNome() == null || passagem.getNome().isEmpty())
            return false;

        return true;
    }
    public String calcularTipoDePassagem(int idade) {
        if (idade < 6 || idade > 60) {
            return "Paga meia";
        } else {
            return "Paga inteira";
        }
    }
}

