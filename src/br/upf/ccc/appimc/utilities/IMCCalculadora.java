/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.upf.ccc.appimc.utilities;

import br.upf.ccc.appimc.model.Pessoa;

/**
 *
 * @author gabriel
 */
public class IMCCalculadora {
    public static double calcularIMC(Pessoa pessoa){
        return pessoa.getPeso() / (pessoa.getAltura() * pessoa.getAltura());
    }
    
    public static String interpretarIMC(double imc){
        if (imc < 18.5) return "Magreza";
        else if(imc < 24.9) return "Normal";
        else if(imc < 29.9) return "Sobrepeso";
        else if(imc < 34.9) return "Obesidade Grau I";
        else if(imc < 39.9) return "Obesidade Grau II";
        else return "Obesidade Grau III";
    }
    
}
