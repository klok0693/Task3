package com.company.company.service.dto;

import com.company.company.util.NotNullByDefault;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.sql.DataSource;
import java.sql.SQLException;

@NotNullByDefault
public class JasperCompiler {

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;


    public void createPDFReport(String reportWay, String fileName) throws JRException, SQLException {

        JasperReport report = getJReport(reportWay);
        JasperPrint print = getJSPrint(report);

        JasperExportManager.exportReportToPdfFile(print, fileName);

        //exportCsvReport(getJSPrint(report),fileName);
    }


    private void exportCsvReport(JasperPrint jasperPrint, String fileName) throws JRException {

        JRCsvExporter exporter = new JRCsvExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleWriterExporterOutput(fileName));

        exporter.exportReport();
    }


    private JasperPrint getJSPrint(JasperReport report) throws SQLException, JRException {
        return JasperFillManager.fillReport(report, null, dataSource.getConnection());
    }


    private JasperReport getJReport(String way) throws JRException {
        return JasperCompileManager.compileReport(JasperCompiler.class.getResourceAsStream(way));
    }


    private void saveReport(JasperReport jasperReport, String fileName) throws JRException {
        JRSaver.saveObject(jasperReport, fileName);
    }
}
