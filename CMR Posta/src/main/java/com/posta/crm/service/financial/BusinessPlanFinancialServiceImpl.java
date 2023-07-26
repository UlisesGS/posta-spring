/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.service.financial;

import com.posta.crm.entity.financiero.BusinessPlanFinancial;
import com.posta.crm.entity.financiero.GastoCosto;
import com.posta.crm.entity.financiero.PlanInversion;
import com.posta.crm.entity.financiero.PresupuestoCompra;
import com.posta.crm.entity.financiero.PresupuestoVenta;
import com.posta.crm.entity.financiero.partes.CiclicidadVentas;
import com.posta.crm.entity.financiero.partes.EstructuraCompras;
import com.posta.crm.entity.financiero.partes.EstructuraMercado;
import com.posta.crm.entity.financiero.partes.Inversion;
import com.posta.crm.entity.financiero.partes.OtrosCostos;
import com.posta.crm.entity.financiero.partes.Personal;
import com.posta.crm.entity.financiero.partes.RequerimientosPersonal;
import com.posta.crm.repository.financial.BusinessPlanFinancialRepository;
import com.posta.crm.repository.financial.CiclicidadVentasRepository;
import com.posta.crm.repository.financial.EstructuraComprasRepository;
import com.posta.crm.repository.financial.EstructuraMercadoRepository;
import com.posta.crm.repository.financial.GastoCostoRepository;
import com.posta.crm.repository.financial.InversionRepository;
import com.posta.crm.repository.financial.OtrosCostosRepository;
import com.posta.crm.repository.financial.PersonalRepository;
import com.posta.crm.repository.financial.PlanInversionRepository;
import com.posta.crm.repository.financial.PresupuestoCompraRepository;
import com.posta.crm.repository.financial.PresupuestoVentaRepository;
import com.posta.crm.repository.financial.RequerimientosPersonalRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

/**
 *
 * @author crowl
 */
@Service
public class BusinessPlanFinancialServiceImpl implements IBusinessPlanFinancialService {

    @Autowired
    private BusinessPlanFinancialRepository businessPlanFinancialRepository;
    //Ventas
    @Autowired
    private PresupuestoVentaRepository presupuestoVentaRepository;
    @Autowired
    private EstructuraMercadoRepository estructuraMercadoRepository;
    @Autowired
    private CiclicidadVentasRepository ciclicidadVentasRepository;
    //Compras
    @Autowired
    private PresupuestoCompraRepository presupuestoCompraRepository;
    @Autowired
    private EstructuraComprasRepository estructuraComprasRepository;
    //Gastos
    @Autowired
    private PersonalRepository personalRepository;
    @Autowired
    private RequerimientosPersonalRepository requerimientosPersonalRepository;
    @Autowired
    private GastoCostoRepository gastoCostoRepository;
    //Otros Gastos
    @Autowired
    private OtrosCostosRepository otrosCostosRepository;
    //Plan Inversion
    @Autowired
    private InversionRepository inversionRepository;
    @Autowired
    private PlanInversionRepository planInversionRepository;
    
    
    

    @Override
    public BusinessPlanFinancial save(BusinessPlanFinancial businessPlanFinancial) {
        List<EstructuraMercado> estructuraSave = businessPlanFinancial.getPresupuestoVenta().getEstructuraMercado();
        List<EstructuraMercado> estructuraUpdate = new ArrayList();
        List<CiclicidadVentas> ciclicidadVentas = businessPlanFinancial.getPresupuestoVenta().getCiclicidadVentas();
        List<CiclicidadVentas> ciclicidadUpdate = new ArrayList();
        for (EstructuraMercado estructuraMercado : estructuraSave) {
            estructuraMercado.calculos();
            estructuraUpdate.add(estructuraMercadoRepository.save(estructuraMercado));
        }
        for (CiclicidadVentas ciclicidadVenta : ciclicidadVentas) {
            ciclicidadUpdate.add(ciclicidadVentasRepository.save(ciclicidadVenta));
        }
        PresupuestoVenta presupuestoVentaUpdate = businessPlanFinancial.getPresupuestoVenta();

        presupuestoVentaUpdate.setEstructuraMercado(estructuraUpdate);
        presupuestoVentaUpdate.setCiclicidadVentas(ciclicidadUpdate);

        presupuestoVentaUpdate.calcular();
        presupuestoVentaUpdate.calculosCiclicidad();

        businessPlanFinancial.setPresupuestoVenta(presupuestoVentaRepository.save(presupuestoVentaUpdate));

        return businessPlanFinancialRepository.save(businessPlanFinancial);
    }

    @Override
    public Optional<BusinessPlanFinancial> findByID(Long id) {
        return businessPlanFinancialRepository.findById(id);
    }

    @Override
    public BusinessPlanFinancial updateFinancial(BusinessPlanFinancial businessPlanFinancial, Long id) {
        BusinessPlanFinancial businessPlanFinancialUpdate = businessPlanFinancialRepository.findById(id).get();
        businessPlanFinancialUpdate= businessPlanFinancial;
        return businessPlanFinancialRepository.save(businessPlanFinancialUpdate);
    }

    @Override
    public BusinessPlanFinancial updateCompras(BusinessPlanFinancial businessPlanFinancial, Long id) {
        BusinessPlanFinancial businessPlanFinancialUpdate = businessPlanFinancialRepository.findById(id).get();
        List<PresupuestoCompra> presupuestoCompra = businessPlanFinancial.getPresupuestoCompra();

        List<PresupuestoCompra> presupuestoCompraUpdate = new ArrayList();

        List<EstructuraCompras> estructuraComprasUpdate = new ArrayList();

        List<EstructuraMercado> estructuraMercado = businessPlanFinancialUpdate.getPresupuestoVenta().getEstructuraMercado();

        for (PresupuestoCompra presupuestoCompra1 : presupuestoCompra) {
            for (EstructuraCompras estructura : presupuestoCompra1.getEstructuraCompras()) {
                if (!presupuestoCompra1.getEstructuraCompras().contains(estructura)) {
                    presupuestoCompra1.getEstructuraCompras().add(estructura);
                }
                estructura.calculoTotal();
                estructuraComprasUpdate.add(estructuraComprasRepository.save(estructura));
            }
            
            presupuestoCompraUpdate.add(presupuestoCompraRepository.save(presupuestoCompra1));
        }

        for (int i = 0; i < presupuestoCompraUpdate.size(); i++) {
            presupuestoCompraUpdate.get(i).setNombreProcucto(estructuraMercado.get(i).getProducto());
            presupuestoCompraUpdate.get(i).setTipoProducto(estructuraMercado.get(i).getTipo().toString());
            presupuestoCompraUpdate.get(i).setCantidadProducto(estructuraMercado.get(i).getCantidad());
            presupuestoCompraUpdate.get(i).sacarTotales();
            presupuestoCompraRepository.save(presupuestoCompraUpdate.get(i));
        }

        businessPlanFinancialUpdate.setPresupuestoCompra(presupuestoCompraUpdate);
       
        businessPlanFinancialUpdate.cuentas();
        return businessPlanFinancialRepository.save(businessPlanFinancialUpdate);
    }

    @Override
    public BusinessPlanFinancial updateGastos(BusinessPlanFinancial businessPlanFinancial, Long id) {
        BusinessPlanFinancial businessPlanFinancialUpdate = businessPlanFinancialRepository.findById(id).get();
        //Listas de Personal
        List<Personal>personalOperativo=businessPlanFinancial.getGastoCosto().getOperativo().getPersonal();
        List<Personal>personalAdministrativo=businessPlanFinancial.getGastoCosto().getAdministrativo().getPersonal();
        List<Personal>personalComercial=businessPlanFinancial.getGastoCosto().getComercialVentas().getPersonal();
        
        RequerimientosPersonal requerimientosOperativo=new RequerimientosPersonal();
        RequerimientosPersonal requerimientosAdministrativo=new RequerimientosPersonal();
        RequerimientosPersonal requerimientosComercialVentas=new RequerimientosPersonal();
        
        GastoCosto gastoCostoUpdate=businessPlanFinancial.getGastoCosto();
        
        //Nuevas Listas de Personal para updatear
        List<Personal>operativoUpdate= new ArrayList();
        List<Personal>administrativoUpdate= new ArrayList();
        List<Personal>comercialUpdate= new ArrayList();
        
        for (Personal personal : personalOperativo) {
            if(!personalOperativo.contains(personal)){
                operativoUpdate.add(personal);        
            }
            personal.anual();
            operativoUpdate.add(personalRepository.save(personal));
        }
        for (Personal personal : personalAdministrativo) {
            if(!personalAdministrativo.contains(personal)){
                administrativoUpdate.add(personal);        
            }
            personal.anual();
            administrativoUpdate.add(personalRepository.save(personal));
        }
        for (Personal personal : personalComercial) {
            if(!personalComercial.contains(personal)){
                comercialUpdate.add(personal);        
            }
            personal.anual();
            comercialUpdate.add(personalRepository.save(personal));
        }
        
        
        //Metodos de Otros Gastos
        //Traidas del Front
        List<OtrosCostos>costosOperativos=businessPlanFinancial.getGastoCosto().getOperativo().getCostos();
        List<OtrosCostos>costosAdministrativos=businessPlanFinancial.getGastoCosto().getAdministrativo().getCostos();
        List<OtrosCostos>costosComercial=businessPlanFinancial.getGastoCosto().getComercialVentas().getCostos();
        
        //Para guardar en Back
        List<OtrosCostos>costosOperativosUpdate=new ArrayList();
        List<OtrosCostos>costosAdministrativosUpdate=new ArrayList();
        List<OtrosCostos>costosComercialUpdate=new ArrayList();
        
        
        for (OtrosCostos costosOperativo : costosOperativos) {
            if(!costosOperativos.contains(costosOperativo)){
                costosOperativosUpdate.add(costosOperativo);        
            }
            costosOperativo.anual();
            costosOperativosUpdate.add(otrosCostosRepository.save(costosOperativo));
        }
        for (OtrosCostos costosAdministrativo : costosAdministrativos) {
            if(!costosAdministrativos.contains(costosAdministrativo)){
                costosAdministrativosUpdate.add(costosAdministrativo);        
            }
            costosAdministrativo.anual();
            costosAdministrativosUpdate.add(otrosCostosRepository.save(costosAdministrativo));
        }
        for (OtrosCostos costoComercial : costosComercial) {
            if(!costosComercial.contains(costoComercial)){
                costosComercialUpdate.add(costoComercial);        
            }
            costoComercial.anual();
            costosComercialUpdate.add(otrosCostosRepository.save(costoComercial));
        }
        
        requerimientosOperativo.setPersonal(operativoUpdate);
        requerimientosOperativo.parafiscalesCalculos();
        requerimientosOperativo.setCostos(costosOperativosUpdate);
        requerimientosOperativo.totalCostos();
        requerimientosPersonalRepository.save(requerimientosOperativo);
        
        requerimientosAdministrativo.setPersonal(administrativoUpdate);
        requerimientosAdministrativo.parafiscalesCalculos();
        requerimientosAdministrativo.setCostos(costosAdministrativosUpdate);
        requerimientosAdministrativo.totalCostos();
        requerimientosPersonalRepository.save(requerimientosAdministrativo);
        
        requerimientosComercialVentas.setPersonal(comercialUpdate);
        requerimientosComercialVentas.parafiscalesCalculos();
        requerimientosComercialVentas.setCostos(costosComercialUpdate);
        requerimientosComercialVentas.totalCostos();
        requerimientosPersonalRepository.save(requerimientosComercialVentas);
        
        
        gastoCostoUpdate.setOperativo(requerimientosOperativo);
        gastoCostoUpdate.setAdministrativo(requerimientosAdministrativo);
        gastoCostoUpdate.setComercialVentas(requerimientosComercialVentas);
        
        gastoCostoRepository.save(gastoCostoUpdate);
        
        businessPlanFinancialUpdate.setGastoCosto(gastoCostoUpdate);
        return businessPlanFinancialRepository.save(businessPlanFinancialUpdate);
    }

    @Override
    public BusinessPlanFinancial updateInversion(BusinessPlanFinancial businessPlanFinancial, Long id) {
        BusinessPlanFinancial businessPlanFinancialUpdate = businessPlanFinancialRepository.findById(id).get();
        //Listas traidas del Front
        List<Inversion>activoFijo=businessPlanFinancial.getPlanInversion().getActivoFijo();
        List<Inversion>maquinaria=businessPlanFinancial.getPlanInversion().getMaquinariaEquipo();
        List<Inversion>muebles=businessPlanFinancial.getPlanInversion().getMueblesEnseres();
        List<Inversion>vehiculo=businessPlanFinancial.getPlanInversion().getVehiculos();
        //Listas nuevas
        List<Inversion>activoFijoUpdate=new ArrayList();
        List<Inversion>maquinariaUpdate=new ArrayList();
        List<Inversion>muebleUpdate=new ArrayList();
        List<Inversion>vehiculoUpdate=new ArrayList();
        
        PlanInversion planInversionUpdate=new PlanInversion();
        
        for (Inversion activo : activoFijo) {
            if(!activoFijo.contains(activo)){
                activoFijoUpdate.add(activo);
            }
            activo.totalCredito();
            activoFijoUpdate.add(inversionRepository.save(activo));
        }
         for (Inversion activo : maquinaria) {
             if(!maquinaria.contains(activo)){
                 maquinariaUpdate.add(activo);
             }
            activo.totalCredito();
            maquinariaUpdate.add(inversionRepository.save(activo));
        }
          for (Inversion activo : muebles) {
              if(!muebles.contains(activo)){
                  muebleUpdate.add(activo);
              }
            activo.totalCredito();
            muebleUpdate.add(inversionRepository.save(activo));
        }
          for (Inversion activo : vehiculo) {
              if(!vehiculo.contains(activo)){
                  vehiculoUpdate.add(activo);
              }
            activo.totalCredito();
            vehiculoUpdate.add(inversionRepository.save(activo));
        }
          
          planInversionUpdate.setActivoFijo(activoFijoUpdate);
          planInversionUpdate.setMaquinariaEquipo(maquinariaUpdate);
          planInversionUpdate.setMueblesEnseres(muebleUpdate);
          planInversionUpdate.setVehiculos(vehiculoUpdate);
          planInversionUpdate.fijo();
          planInversionUpdate.maquinaria();
          planInversionUpdate.muebles();
          planInversionUpdate.vehiculos();
          planInversionUpdate.calculoTotal();
          planInversionRepository.save(planInversionUpdate);
          
          businessPlanFinancialUpdate.setPlanInversion(planInversionUpdate);
         return businessPlanFinancialRepository.save(businessPlanFinancialUpdate);
    }

}
