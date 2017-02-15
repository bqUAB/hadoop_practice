package org.myorg;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AverageTemperatureReducer 
  extends Reducer<Text, TemperatureAveragingPair, Text, IntWritable> {
//  extends Reducer<Text, IntWritable, Text, IntWritable> {
  private IntWritable average = new IntWritable();

  @Override
  protected void reduce(Text key, Iterable<TemperatureAveragingPair> values, Context context)
//  protected void reduce(Text key, Iterable<IntWritable> values, Context context)
    throws IOException, InterruptedException {
      int temp = 0;
      int count = 0;
      for (TemperatureAveragingPair pair : values) {
//      for (IntWritable value : values) {
          temp += pair.getTemp().get();
          count += pair.getCount().get();
//          temp += value.get();
//          count += ;
      }
      average.set(temp / count);
      context.write(key, average);
//      int avgValue = sum / cont;
//      context.write(key, avgValue);
  }
}
