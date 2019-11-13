package com.tx.txcall.common.utils.office;


import com.tx.txcall.common.utils.office.bean.dto.DocHtmlDto;

import java.io.IOException;

public interface Doc2Html {

	DocHtmlDto doc2Html(String filePath) throws IOException;
}
