package kr.or.iei.product.model.vo;

import java.util.List;

import javax.annotation.security.DenyAll;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductFileListData {
	private List productFileList;
}
