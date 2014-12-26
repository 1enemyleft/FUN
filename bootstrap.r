# Not using boot library to utlize bootstrap 

#assign to data then use following fuctions 
bootsd <- function(data, feature) {

  #data is your data frame 
  #feature is the variable you want bootstrap from 
  
  #set seed is to make your result reproducible
  set.seed(687)
  
  #extract population mean
  pop.mean = mean(data$feature)
  print(paste("Population mean is ", pop.mean)) 
 
  bootmeans <- numeric()
  #set sample size to be sample_pct of whole data
  #this is sampling 35% to 75% of the sample size, can be changed to random/different sizes
  
  for (sample_pct in 0.05*(7:15)) {
    #create variable to store the bootstrap means 
    data.bootmeans <- numeric()
    #bootstrap 1000 times, theoritically should be the same size as the population, but as n >= 1000, the result converge very quickly
    n = 1000
    for (i in 1:n) {
      sample_size = floor(sample_pct*nrow(data))      
      data.bootsample <- sample(data$revenue, sample_size, replace=T) 
      data.bootmeans[i] <- mean(data.bootsample)
    }
   bootmeans <- rbind(bootmeans, data.bootmeans) 
  }
  
  #calculate the standard diveation from population mean 
  data.sd = sqrt((1/n)*sum((bootmeans - pop.mean)^2))
  print(paste("Bootstrap standard deviation is ", data.sd))
  
  #this function give back the bootstrap sample standard deviations 
  return(data.sd)
}
