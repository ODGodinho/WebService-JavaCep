package CEPService;

import java.rmi.RemoteException;

import CEPService_pkg.CEPServiceLocator;
import CEPService_pkg.CEPServicePort;

public class CEPServicePortProxy implements CEPService_pkg.CEPServicePort {
  private String _endpoint = null;
  private CEPServicePort cEPServicePort = null;
  
  public CEPServicePortProxy() {
    _initCEPServicePortProxy();
  }
  
  public CEPServicePortProxy(String endpoint) {
    _endpoint = endpoint;
    _initCEPServicePortProxy();
  }
  
  private void _initCEPServicePortProxy() {
    try {
      cEPServicePort = (new CEPServiceLocator()).getCEPServicePort();
      if (cEPServicePort != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)cEPServicePort)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)cEPServicePort)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (cEPServicePort != null)
      ((javax.xml.rpc.Stub)cEPServicePort)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public CEPServicePort getCEPServicePort() {
    if (cEPServicePort == null)
      _initCEPServicePortProxy();
    return cEPServicePort;
  }

public String obterVersao() throws RemoteException {
	// TODO Auto-generated method stub
	return null;
}

public String obterLogradouro(String cep) throws RemoteException {
	// TODO Auto-generated method stub
	return null;
}

public String obterLogradouroAuth(String cep, String usuario, String senha)
		throws RemoteException {
	// TODO Auto-generated method stub
	return null;
}

public String[] obterCEP(String logradouro, String localidade, String UF)
		throws RemoteException {
	// TODO Auto-generated method stub
	return null;
}

public String[] obterCEPAuth(String logradouro, String localidade, String UF,
		String usuario, String senha) throws RemoteException {
	// TODO Auto-generated method stub
	return null;
}
  
  
  
//  MY CODE
  
  
}