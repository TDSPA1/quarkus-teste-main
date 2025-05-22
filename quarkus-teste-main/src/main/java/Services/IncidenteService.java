package Services;

import Entidades.Incidente;

public class IncidenteService {
    public boolean validateIncidente(Incidente incidente) {
        if(incidente == null)
            return false;
        if(incidente.getTransporte() == null || incidente.getTransporte().isEmpty())
            return false;

        return true;
    }
}


