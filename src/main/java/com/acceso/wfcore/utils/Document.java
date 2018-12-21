package com.acceso.wfcore.utils;

public class Document {
   private String nombre;
   private Integer codModulo;
   private Integer orden;
   private Integer categoria;

   public Document(String nombre, Integer codModulo, Integer orden, Integer categoria) {
      this.nombre = nombre;
      this.codModulo = codModulo;
      this.orden = orden;
      this.categoria = categoria;
   }

   public String getNombre() {
      return nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public Integer getCodModulo() {
      return codModulo;
   }

   public void setCodModulo(Integer codModulo) {
      this.codModulo = codModulo;
   }

   public Integer getOrden() {
      return orden;
   }

   public void setOrden(Integer orden) {
      this.orden = orden;
   }

   public Integer getCategoria() {
      return categoria;
   }

   public void setCategoria(Integer categoria) {
      this.categoria = categoria;
   }
}
