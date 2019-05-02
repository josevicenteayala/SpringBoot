/**
 * 
 */
package com.excel.base.utils;

import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

/**
 * @author vin00
 *
 */
@Component
public class UploadExcelUtil {

	/**
	 * @param iterableRows
	 * @param parallel
	 * @return
	 */
	public Supplier<Stream<Row>> getRowStreamSupplier(Iterable<Row> iterableRows, boolean parallel){
		return () -> getStream(iterableRows, parallel);
	}
	
	/**
	 * @param iterable
	 * @param parallel
	 * @return
	 */
	public <T> Stream<T> getStream(Iterable<T> iterable, boolean parallel){
		return StreamSupport.stream(iterable.spliterator(), parallel);
	}
	
	/**
	 * @param end
	 * @return
	 */
	public Supplier<Stream<Integer>> cellIteratorSupplier(int end) {
		return () -> numberStream(end);
	}
	
	/**
	 * @param end
	 * @return
	 */
	public Stream<Integer> numberStream(int end) {
		return IntStream.range(0, end).boxed();
	}
	
}
