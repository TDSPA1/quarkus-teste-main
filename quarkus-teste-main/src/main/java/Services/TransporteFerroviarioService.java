package Services;

import Entidades.TransporteFerroviario;

public class TransporteFerroviarioService {
    public boolean validateTransporteFerroviario(TransporteFerroviario transporteFerroviario) {
        if(transporteFerroviario == null)
            return false;
        if(transporteFerroviario.getNome() == null || transporteFerroviario.getNome().isEmpty())
            return false;

        return true;
    }
}

