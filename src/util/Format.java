package util;
import java.sql.Date;
import java.time.LocalDate;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;

public class Format {

  public static Date converterParaSqlDate(String dataTexto){
      //Definindo formato
      DateTimeFormatter formatBrasil = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      //Aplicando o formato
      LocalDate data = LocalDate.parse(dataTexto, formatBrasil);
      //convertento a data para o banco de dados(SQL)
      return Date.valueOf(data);
  }  
  
}
