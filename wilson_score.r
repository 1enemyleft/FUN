Wilson_Score <- function(n, p, confidence){
  # n is the size of your sample
  # p is the probability of one instance, ie the metric measuring
  # confidence is the confidence interval 
  
  if (confidence == 0.99) {
    z = 2.576
  } else {
    if (confidence == 0.95) {
      z = 1.960
    } else {
      if (confidence == 0.90){
        z = 1.640      
      } else {
        print("Please enter a valid confidence interval in 0.99, 0.95 or 0.90")
      }
    }
  }
  # wilson score equation is following: http://en.wikipedia.org/wiki/Binomial_proportion_confidence_interval
  wilson <- (1 / (1 + (z^2)/n))*(p + (z^2)/(2*n) - z * sqrt((p*(1-p))/n + (z^2)/(4*(n^2))))
  return(wilson)
}
