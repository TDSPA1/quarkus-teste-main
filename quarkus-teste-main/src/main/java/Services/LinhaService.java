package Services;
import Entidades.Linha;

public class LinhaService{
    public boolean validateLinha(Linha linha) {
        if(linha == null)
            return false;
        if(linha.getNome() == null || linha.getNome().isEmpty())
            return false;

        return true;
    }
}



