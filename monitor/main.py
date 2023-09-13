from log_extractor import LogExtractor
from trace_extractor import TraceExtractor
from datetime import datetime, timedelta

trace_extractor = TraceExtractor("http://localhost:9411/zipkin/")

end_time = datetime.now()
start_time = end_time - timedelta(minutes=120)
limit = 100

trace_extractor.get_traces(start_time, end_time, limit)
trace_extractor.process_traces()
# trace_extractor.save_traces("./traces/F12/normal")
trace_extractor.save_traces("./traces/F15/buggy")
    
log_extractor = LogExtractor()
# log_extractor.extract_all("./logs/F12/normal")
log_extractor.extract_all("./logs/F15/buggy")