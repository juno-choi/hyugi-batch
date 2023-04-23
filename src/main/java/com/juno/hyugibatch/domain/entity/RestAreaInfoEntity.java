package com.juno.hyugibatch.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestAreaInfoEntity {
    private String routeCd;
    private String svarAddr;
    private String routeNm;
    private String hdqrNm;
    private String mtnofNm;
    private String svarCd;
    private String svarNm;
    private String hdqrCd;
    private String mtnofCd;
    private String svarGsstClssCd;
    private String svarGsstClssNm;
    private String gudClssCd;
    private String gudClssNm;
    private String pstnoCd;
    private String cocrPrkgTrcn;
    private String fscarPrkgTrcn;
    private String dspnPrkgTrcn;
    private String bsopAdtnlFcltCd;
    private String rprsTelNo;
}
