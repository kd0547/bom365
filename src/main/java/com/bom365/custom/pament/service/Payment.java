package com.bom365.custom.pament.service;

import java.net.URI;

public interface Payment {
	<T> T  send(URI url,Object body,Class<T> dto);
}
