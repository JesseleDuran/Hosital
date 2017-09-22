/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import connections.DB4OConnection;
import dao.CitaPreviaDAO;
import dao.EmpleadoDAO;
import dao.MedicoDAO;
import dao.PacienteDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import models.CitaPrevia;
import models.Empleado;
import models.Medico;
import models.Paciente;
import views.CitasRegisterView;
import views.ConsultaRegisterView;

import views.LoginView;
import views.EmpleadoMenuView;
import views.EmpleadoRegisterView;
import views.MedicoEspRegisterView;
import views.MedicoMenuView;
import views.MedicoRegisterView;
import views.PacienteRegisterView;
import views.PacienteVerView;
import views.TestTableSortFilter;

/**
 *
 * @author Mota
 */
public class Control implements ActionListener {
    
    private static Control instance;
    
    
    
    DB4OConnection db;
    LoginView ventana_login = new LoginView();
    EmpleadoRegisterView registerEmpleadoView = new EmpleadoRegisterView();
    MedicoRegisterView registerMedicoView = new MedicoRegisterView();
    MedicoEspRegisterView registerMedicoEspView = new MedicoEspRegisterView();
    PacienteRegisterView registerPacienteView = new PacienteRegisterView();
    MedicoMenuView ventana_principal_medico;
    CitasRegisterView citasRegisterView;
    EmpleadoMenuView ventana_principal_empleado;
    EmpleadoDAO empleadoDao = EmpleadoDAO.getInstance();
    PacienteDAO pacienteDao = PacienteDAO.getInstance();
    MedicoDAO medicoDao = MedicoDAO.getInstance();
    CitaPreviaDAO citaDao = CitaPreviaDAO.getInstance();
    PacienteVerView pacienteVerView;
    ConsultaRegisterView consultaRegisterView;
    
    private Control()
    {
        this.ventana_login.cancelarButton.addActionListener(this);
        this.ventana_login.ingresarButton.addActionListener(this);
    }
    
    public static Control getInstance()
    {
        if (instance == null)
            instance = new Control();
        return instance;
    }
    
    public void setDatabase(DB4OConnection db)
    {
        this.db = db;
    }
    
    
    public void abrirVentana(Integer Ventana, String nombre) {
        switch (Ventana) {
            case 1:
                this.ventana_login.setVisible(true);
                
                break;
            case 2:
                this.registerMedicoView.setVisible(true);
                this.registerMedicoView.cancelarButton.addActionListener(this);
                this.registerMedicoView.registrarButton.addActionListener(this);
                break;
            case 3:
                this.registerEmpleadoView.setVisible(true);
                this.registerEmpleadoView.cancelarButton.addActionListener(this);
                this.registerEmpleadoView.registrarButton.addActionListener(this);
                break;
            case 4:
                this.citasRegisterView = new CitasRegisterView(db);
                String nombreColumnas[] = {"Nombre", "Apellido", "Teléfono", "Cédula"};
                DefaultTableModel model = new DefaultTableModel(null, nombreColumnas) {
                    //para que las filas no sean editables
                    @Override
                    public boolean isCellEditable(int fila, int columna) {
                        return false;
                    }

                };
                pacienteDao.mostrarTodos(db, model, this.citasRegisterView.jTablePaciente);
                String nombreColumnas2[] = {"Nombre", "Apellido", "Especialidad", "Licencia médica"};
                DefaultTableModel model2 = new DefaultTableModel(null, nombreColumnas2) {
                    //para que las filas no sean editables
                    @Override
                    public boolean isCellEditable(int fila, int columna) {
                        return false;
                    }

                };
                medicoDao.mostrarTodos(db, model2, this.citasRegisterView.jTableMedicos);
                this.citasRegisterView.setVisible(true);
                this.citasRegisterView.CancelarCita.addActionListener(this);
                this.citasRegisterView.RegistrarCita.addActionListener(this);
                break;
            case 5:
                this.registerPacienteView.setVisible(true);
                this.registerPacienteView.cancelarButton.addActionListener(this);
                this.registerPacienteView.registrarButton.addActionListener(this);
                break;
            case 6:
                this.registerMedicoEspView.setVisible(true);
                this.registerMedicoEspView.cancelarButtonEsp.addActionListener(this);
                this.registerMedicoEspView.registrarButtonEsp.addActionListener(this);
                break;
            
                
            case 8:    
                Paciente pac = new Paciente(null,null,null,nombre);
                Paciente found = pacienteDao.queryOne(db, pac);
                System.out.println(found);
                this.pacienteVerView = new PacienteVerView(found);
                this.pacienteVerView.setVisible(true);
                this.pacienteVerView.registrarConsulta.addActionListener(this);
                this.registerPacienteView.cancelarButton.addActionListener(this);
        }
    }
    
    public void abrirVentana(Integer Ventana, String id_medico, String id) {
        switch (Ventana) {
            case 7:
                this.consultaRegisterView = new ConsultaRegisterView(id_medico, id);
                this.consultaRegisterView.setVisible(true);
                this.consultaRegisterView.cancelarButton.addActionListener(this);
                this.consultaRegisterView.registrarButton.addActionListener(this);
                break;
        }
    }
    

    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()) {
            case "Ingresar":
                ingresarButtonActionPerform(e);
                break;
            case "exit":
                cancelarButtonActionPerformed(e);
                break;
            case "RegistrarUser":
                registrarUserButtonActionPerformed(e);
                break;
            case "CancelarUser":
                this.registerEmpleadoView.dispose();
                break;
            case "RegistrarMedico":
                registrarMedicoButtonActionPerformed(e);
                break;
            case "CancelarMedico":
                this.registerMedicoView.dispose();
                break;
            case "RegistrarPaciente":
                registrarPacienteButtonActionPerformed(e);
                break;
            case "CancelarPaciente":
                this.registerPacienteView.dispose();
                break;
            case "RegistrarCita":
                registrarCitaButtonActionPerformed(e);
                break;
            case "CancelarCita":
                this.citasRegisterView.dispose();
                break;
            case "RegistrarMedicoEsp":
                registrarMedicoEspButtonActionPerformed(e);
                break;
            case "CancelarMedicoEsp":
                this.registerMedicoEspView.dispose();
                break; 
            case "RegistrarCosulta":
                abrirVentana(7,this.ventana_principal_medico.medico.getLicencia(),this.ventana_principal_medico.ci);
                break;
            case "registrarConsultas":
                //registrarConsultasButtonActionPerformed(e);
                
        }
    }

    //registers
    private void registrarCitaButtonActionPerformed(ActionEvent e) {
        if (validacionesCita() != true) {
            Date date = new Date();
            SpinnerDateModel sm = new SpinnerDateModel(date, null, null, Calendar.MINUTE);

            JSpinner spinner = new JSpinner(sm);
            JSpinner.DateEditor de = new JSpinner.DateEditor(spinner, "HH:mm");
            de.getTextField().setEditable(false);
            spinner.setEditor(de);

            HashMap<String, Object> citaComparison = new HashMap<String, Object>();
            citaComparison.put("fecha", DateFormat.getDateInstance().format(this.citasRegisterView.jCalendar1.getDate()));
            citaComparison.put("hora", de.getFormat().format(this.citasRegisterView.inicioJSpinner.getValue()).toString());
            citaComparison.put("id_medico", this.citasRegisterView.licencia);
            citaComparison.put("id_paciente", this.citasRegisterView.ci);

            CitaPrevia cita = new CitaPrevia(DateFormat.getDateInstance().format(this.citasRegisterView.jCalendar1.getDate()),
                    de.getFormat().format(this.citasRegisterView.inicioJSpinner.getValue()).toString(),
                    this.citasRegisterView.licencia,
                    this.citasRegisterView.ci);

            List<CitaPrevia> citaExistente = citaDao.queryByAllProperties(db, citaComparison);
            System.out.println(citaExistente);

            if (citaExistente.isEmpty()) {
                if (citaDao.insert(db, cita) == true) {
                    System.out.println(cita);

                    this.citasRegisterView.dispose();
                    JOptionPane.showMessageDialog(null, "La cita se ha registrado correctamente", "Registro con éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar cita", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error: Esa cita ya existe", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;

            }

        }

    }

    private void registrarPacienteButtonActionPerformed(ActionEvent evt) {
        if (validacionesPaciente() != true) {

            HashMap<String, Object> pacienteComparison = new HashMap<String, Object>();
            pacienteComparison.put("ci", this.registerPacienteView.ciField.getText());

            Paciente paciente = new Paciente(this.registerPacienteView.nameField.getText().toUpperCase(),
                    this.registerPacienteView.apellidoField.getText().toUpperCase(),
                    this.registerPacienteView.telefonoField.getText().toUpperCase(),
                    this.registerPacienteView.ciField.getText());

            List<Paciente> pacienteExistente = pacienteDao.queryByAllProperties(db, pacienteComparison);

            if (pacienteExistente.isEmpty()) {
                if (pacienteDao.insert(db, paciente) == true) {
                    this.registerPacienteView.dispose();
                    JOptionPane.showMessageDialog(null, "El paciente se ha registrado correctamente", "Registro con éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar paciente", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error: Ese paciente ya existe", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;

            }
        }
    }

    private void registrarUserButtonActionPerformed(ActionEvent evt) {
        if (validacionesUser() != true) {

            HashMap<String, Object> empComparison = new HashMap<String, Object>();
            empComparison.put("cedula", this.registerEmpleadoView.ciField.getText());

            Empleado empleado = new Empleado(this.registerEmpleadoView.nameField.getText().toUpperCase(), this.registerEmpleadoView.ciField.getText(), this.registerEmpleadoView.passwordField.getText());

            List<Empleado> empleadoExistente = empleadoDao.queryByAllProperties(db, empComparison);

            if (empleadoExistente.isEmpty()) {
                if (empleadoDao.insert(db, empleado) == true) {
                    this.registerEmpleadoView.dispose();
                    JOptionPane.showMessageDialog(null, "El empleado se ha registrado correctamente", "Registro con éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar empleado", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error: Ese empleado ya existe", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;

            }
        }
    }

    private void registrarMedicoButtonActionPerformed(ActionEvent evt) {
        if (validacionesMedicoGral() != true) {

            HashMap<String, Object> medicoComparison = new HashMap<String, Object>();
            medicoComparison.put("licencia", this.registerMedicoView.licenciaField.getText());

            Medico medico = new Medico(this.registerMedicoView.licenciaField.getText().toUpperCase(),
                    this.registerMedicoView.nameField.getText().toUpperCase(),
                    this.registerMedicoView.apellidoField.getText().toUpperCase(),
                    "General",
                    this.registerMedicoView.passwordField.getText(), false);

            List<Medico> medicoExistente = medicoDao.queryByAllProperties(db, medicoComparison);

            if (medicoExistente.isEmpty()) {
                if (medicoDao.insert(db, medico) == true) {
                    this.registerMedicoView.dispose();
                    JOptionPane.showMessageDialog(null, "El médico se ha registrado correctamente", "Registro con éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar médico", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error: Ese médico ya existe", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;

            }
        }
    }
    
        private void registrarMedicoEspButtonActionPerformed(ActionEvent evt) {
        if (validacionesMedicoGral() != true) {

            HashMap<String, Object> medicoComparison = new HashMap<String, Object>();
            medicoComparison.put("licencia", this.registerMedicoView.licenciaField.getText());

            Medico medico = new Medico(this.registerMedicoView.licenciaField.getText().toUpperCase(),
                    this.registerMedicoView.nameField.getText().toUpperCase(),
                    this.registerMedicoView.apellidoField.getText().toUpperCase(),
                    this.registerMedicoEspView.especialidadField.getText().toUpperCase(),
                    this.registerMedicoView.passwordField.getText(), true);

            List<Medico> medicoExistente = medicoDao.queryByAllProperties(db, medicoComparison);

            if (medicoExistente.isEmpty()) {
                if (medicoDao.insert(db, medico) == true) {
                    this.registerMedicoView.dispose();
                    JOptionPane.showMessageDialog(null, "El médico se ha registrado correctamente", "Registro con éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar médico", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error: Ese médico ya existe", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;

            }
        }
    }

    //login
    public void ingresarButtonActionPerform(ActionEvent e) {

        if (!this.ventana_login.ciField.getText().trim().equals("")
                && !this.ventana_login.passwordField.getText().trim().equals("")) {

            Empleado empleado = new Empleado(null, this.ventana_login.ciField.getText(), this.ventana_login.passwordField.getText());
            Medico medico = new Medico(this.ventana_login.ciField.getText(),null,null,null,this.ventana_login.passwordField.getText(), false);

            Empleado empLoggeado = EmpleadoDAO.getInstance().queryOne(db, empleado);
            Medico medicLoggeado = MedicoDAO.getInstance().queryOne(db, medico);

            if (empLoggeado != null) {
                System.out.println("soy empleado");
                ventana_login.dispose();
                try {
                    this.ventana_principal_empleado = new EmpleadoMenuView(empLoggeado.getNombre());
                    this.ventana_principal_empleado.registrarCitaPreviaButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            abrirVentana(4,"");
                        }
                    });

                } catch (Exception ex) {
                    Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.ventana_principal_empleado.setVisible(true);
                addRegistrar();
                addVer();

            } else if(medicLoggeado != null)
            {     
                try {
                    System.out.println("soy doctor especial");
                    ventana_login.dispose();
                    String nombreColumnas2[] = {"Fecha", "Hora", "Cédula del paciente"};
                    DefaultTableModel model2 = new DefaultTableModel(null, nombreColumnas2) {
                        //para que las filas no sean editables
                        @Override
                        public boolean isCellEditable(int fila, int columna) {
                            return false;
                        }

                    };
                    
                    this.ventana_principal_medico = new MedicoMenuView(medicLoggeado);
                    List<CitaPrevia> citas = citaDao.mostrarTodos(db, model2, this.ventana_principal_medico.citasTable, medicLoggeado.getLicencia());
                    this.ventana_principal_medico.verUsuarioButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if(ventana_principal_medico.citasTable.getSelectedRow() >= 0)
                            {
                                CitaPrevia c = citas.get(ventana_principal_medico.citasTable.getSelectedRow());
                            abrirVentana(8, c.getId_paciente());
                            }
                            
                        }
                    });

                } catch (Exception ex) {
                    Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.ventana_principal_medico.setVisible(true);

            } else {
                System.out.println("soy nadie");
                JOptionPane.showMessageDialog(null, "El nombre de usuario y contraseña no coinciden, por favor vuelva a intentarlo",
                        "Error al Iniciar Sesión",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, llenar todos los campos",
                    "Error al Iniciar Sesión",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    public void cancelarButtonActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    private void addRegistrar() {
        JMenuItem medico = new JMenuItem("Médico General");
        medico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentana(2, "");
            }
        });
        this.ventana_principal_empleado.registrarMenu.add(medico);

        JMenuItem medico1 = new JMenuItem("Médico Especialista");
        medico1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentana(6,"");
            }
        });
        this.ventana_principal_empleado.registrarMenu.add(medico1);

        JMenuItem empleado = new JMenuItem("Empleado");
        empleado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentana(3,"");
            }
        });
        this.ventana_principal_empleado.registrarMenu.add(empleado);

        JMenuItem paciente = new JMenuItem("Paciente");
        paciente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentana(5,"");
            }
        });
        this.ventana_principal_empleado.registrarMenu.add(paciente);
    }

    private void addVer() {
        JMenuItem medico = new JMenuItem("Médico");
        medico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombreColumnas[] = {"Nombre", "Apellido", "Especialidad", "Cédula"};
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        JFrame frame = new TestTableSortFilter(nombreColumnas,
                                medicoDao.dataMatrix(db), new TestTableSortFilter.TableActions() {

                            @Override
                            public void onEdit(int selected) {
                                // TODO Edit
                            }

                            @Override
                            public boolean onDelete(int selected) {
                                db.open();
                                Medico m = medicoDao.getAll(db).get(selected);
                                db.close();
                                if (selected >= 0)   // TODO add Confirmacion 
                                    medicoDao.delete(db, m);
                                
                                return true;
                            }
                        });
                        frame.setVisible(true);
                    }

                });
            }
        });
        this.ventana_principal_empleado.verMenu.add(medico);

        JMenuItem empleado = new JMenuItem("Empleado");
        empleado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombreColumnas[] = {"Nombre", "Cédula"};
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        JFrame frame = new TestTableSortFilter(nombreColumnas, empleadoDao.dataMatrix(db), new TestTableSortFilter.TableActions() {

                            @Override
                            public void onEdit(int selected) {
                                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }

                            @Override
                            public boolean onDelete(int selected) {
                               db.open();
                               Empleado d = empleadoDao.getAll(db).get(selected);
                               db.close();
                               if(selected >= 0)
                               empleadoDao.delete(db, d);
                               
                               return true;
                            }
                        } );
                        frame.setVisible(true);
                    }

                });
            }
        });
        this.ventana_principal_empleado.verMenu.add(empleado);

        JMenuItem paciente = new JMenuItem("Paciente");
        paciente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombreColumnas[] = {"Nombre", "Apellido", "Teléfono", "Cédula"};
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        JFrame frame = new TestTableSortFilter(nombreColumnas,pacienteDao.dataMatrix(db), new TestTableSortFilter.TableActions() {

                            @Override
                            public void onEdit(int selected) {
                                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }

                            @Override
                            public boolean onDelete(int selected) {
                                db.open();
                                Paciente p = pacienteDao.getAll(db).get(selected);
                                db.close();
                                if(selected >= 0)
                                    pacienteDao.delete(db, p);
                                
                                return true;
                            }
                        } );
                        frame.setVisible(true);
                    }

                });

            }
        });
        this.ventana_principal_empleado.verMenu.add(paciente);
    }

    public boolean validacionesUser() {
        if (this.registerEmpleadoView.nameField.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar un nombre", "ERROR", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (this.registerEmpleadoView.ciField.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar una cédula", "ERROR", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (this.registerEmpleadoView.passwordField.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar una clave", "ERROR", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        return false;
    }

    public boolean validacionesPaciente() {
        if (this.registerPacienteView.nameField.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar un nombre", "ERROR", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (this.registerPacienteView.ciField.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar una cédula", "ERROR", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (this.registerPacienteView.telefonoField.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar un telefono", "ERROR", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (this.registerPacienteView.apellidoField.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar un apellido", "ERROR", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        return false;
    }

    public boolean validacionesMedicoGral() {
        if (this.registerMedicoView.nameField.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar un nombre", "ERROR", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (this.registerMedicoView.licenciaField.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar una cédula", "ERROR", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (this.registerMedicoView.apellidoField.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar un apellido", "ERROR", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (this.registerMedicoView.passwordField.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar una clave", "ERROR", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        return false;
    }
    
    public boolean validacionesMedicoEsp() {
        if (this.registerMedicoEspView.nameField.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar un nombre", "ERROR", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (this.registerMedicoEspView.licenciaField.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar una cédula", "ERROR", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (this.registerMedicoEspView.apellidoField.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar un apellido", "ERROR", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (this.registerMedicoEspView.especialidadField.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar una especialidad", "ERROR", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (this.registerMedicoEspView.passwordField.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar una clave", "ERROR", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        return false;
    }

    public boolean validacionesCita() {
        if (this.citasRegisterView.ci == null) {
            JOptionPane.showMessageDialog(null, "Error: Debe escoger un paciente", "ERROR", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (this.citasRegisterView.licencia == null) {
            JOptionPane.showMessageDialog(null, "Error: Debe escoger un médico", "ERROR", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        /*if (this.registerMedicoView.apellidoField.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar un apellido", "ERROR", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (this.registerMedicoView.especialidadField.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar una especialidad", "ERROR", JOptionPane.ERROR_MESSAGE);
            return true;
        }*/
        return false;
    }

}
