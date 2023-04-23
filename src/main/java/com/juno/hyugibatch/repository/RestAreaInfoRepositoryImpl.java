package com.juno.hyugibatch.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juno.hyugibatch.domain.entity.RestAreaInfoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RestAreaInfoRepositoryImpl implements RestAreaInfoRepository{
    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void save(List<RestAreaInfoEntity> list) {
        int size = list.size();
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO rest_area_info ");
        sb.append("(");
        sb.append("route_cd,");
        sb.append("svar_addr,");
        sb.append("route_nm,");
        sb.append("hdqr_nm,");
        sb.append("mtnof_nm,");
        sb.append("svar_cd,");
        sb.append("svar_nm,");
        sb.append("hdqr_cd,");
        sb.append("mtnof_cd,");
        sb.append("svar_gsst_clss_cd,");
        sb.append("svar_gsst_clss_nm,");
        sb.append("gud_clss_cd,");
        sb.append("gud_clss_nm,");
        sb.append("pstno_cd,");
        sb.append("cocr_prkg_trcn,");
        sb.append("fscar_prkg_trcn,");
        sb.append("dspn_prkg_trcn,");
        sb.append("bsop_adtnl_fclt_cd,");
        sb.append("rprs_tel_no");
        sb.append(") ");
        sb.append("VALUES ");
        for(int i=0; i<size; i++){
            RestAreaInfoEntity entity = objectMapper.convertValue(list.get(i), RestAreaInfoEntity.class);
            sb.append("(");

            sb.append("\'");
            sb.append(entity.getRouteCd().trim());
            sb.append("\'");
            sb.append(",");
            sb.append("\'");
            sb.append(entity.getSvarAddr().trim());
            sb.append("\'");
            sb.append(",");
            sb.append("\'");
            sb.append(entity.getRouteNm().trim());
            sb.append("\'");
            sb.append(",");
            sb.append("\'");
            sb.append(entity.getHdqrNm().trim());
            sb.append("\'");
            sb.append(",");
            sb.append("\'");
            sb.append(entity.getMtnofNm().trim());
            sb.append("\'");
            sb.append(",");
            sb.append("\'");
            sb.append(entity.getSvarCd().trim());
            sb.append("\'");
            sb.append(",");
            sb.append("\'");
            sb.append(entity.getSvarNm().trim());
            sb.append("\'");
            sb.append(",");
            sb.append("\'");
            sb.append(entity.getHdqrCd().trim());
            sb.append("\'");
            sb.append(",");
            sb.append("\'");
            sb.append(entity.getMtnofCd().trim());
            sb.append("\'");
            sb.append(",");
            sb.append("\'");
            sb.append(entity.getSvarGsstClssCd().trim());
            sb.append("\'");
            sb.append(",");
            sb.append("\'");
            sb.append(entity.getSvarGsstClssNm().trim());
            sb.append("\'");
            sb.append(",");
            sb.append("\'");
            sb.append(entity.getGudClssCd().trim());
            sb.append("\'");
            sb.append(",");
            sb.append("\'");
            sb.append(entity.getGudClssNm().trim());
            sb.append("\'");
            sb.append(",");
            sb.append("\'");
            sb.append(entity.getPstnoCd().trim());
            sb.append("\'");
            sb.append(",");
            sb.append("\'");
            sb.append(entity.getCocrPrkgTrcn().trim());
            sb.append("\'");
            sb.append(",");
            sb.append("\'");
            sb.append(entity.getFscarPrkgTrcn().trim());
            sb.append("\'");
            sb.append(",");
            sb.append("\'");
            sb.append(entity.getDspnPrkgTrcn().trim());
            sb.append("\'");
            sb.append(",");
            sb.append("\'");
            sb.append(entity.getBsopAdtnlFcltCd().trim());
            sb.append("\'");
            sb.append(",");
            sb.append("\'");
            sb.append(entity.getRprsTelNo().trim());
            sb.append("\'");

            sb.append(")");
            if(i != (size-1)){
                sb.append(", ");
            }
        }

        jdbcTemplate.update(sb.toString());
    }

    @Override
    public void delete() {
        jdbcTemplate.update("delete from rest_area_info;");
    }
}
