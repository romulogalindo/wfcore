package com.acceso.wfcore.dtos;

import com.acceso.wfcore.utils.Values;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Romulo Galindo
 */
@Entity
@NamedNativeQueries({
        @NamedNativeQuery(
                name = Values.QUERYS_NATIVE_SELECT_PAGTITCON,
                query = "select * from frawor4.tcpagtitcon where co_conten = :p_co_conten and co_pagina = :p_co_pagina order by or_pagtit",
                resultClass = PagtitconDTO.class),
        @NamedNativeQuery(
                name = Values.QUERYS_NATIVE_SAVE_PAGTITCON,
                query = "select * from wfsistem.pbpagtitcon_save(:p_co_pagtit, :p_co_pagina, :p_co_conten, :p_no_pagtit, :p_or_pagtit )",
                resultClass = PagtitconDTO.class)
})
public class PagtitconDTO implements Serializable {

    @Id
    int co_pagtit;
    int co_conten;
    int co_pagina;
    String no_pagtit;
    short or_pagtit;

    public PagtitconDTO() {
    }

    public int getCo_pagtit() {
        return co_pagtit;
    }

    public void setCo_pagtit(int co_pagtit) {
        this.co_pagtit = co_pagtit;
    }

    public int getCo_conten() {
        return co_conten;
    }

    public void setCo_conten(int co_conten) {
        this.co_conten = co_conten;
    }

    public int getCo_pagina() {
        return co_pagina;
    }

    public void setCo_pagina(int co_pagina) {
        this.co_pagina = co_pagina;
    }

    public String getNo_pagtit() {
        return no_pagtit;
    }

    public void setNo_pagtit(String no_pagtit) {
        this.no_pagtit = no_pagtit;
    }

    public short getOr_pagtit() {
        return or_pagtit;
    }

    public void setOr_pagtit(short or_pagtit) {
        this.or_pagtit = or_pagtit;
    }
}
