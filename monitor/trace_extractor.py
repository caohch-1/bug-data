import requests
import json
from datetime import datetime, timedelta

class TraceExtractor:
    def __init__(self, endpoint) -> None:
        self.api_url = f"{endpoint}api/v2/traces"
        self.format_traces = []
    
    def get_traces(self, start_time, end_time, limit):
        params = {
            "lookback": int((end_time-start_time).total_seconds() * 1000),
            "endTs": int(end_time.timestamp() * 1000),
            "limit": limit
        }

        response = requests.get(self.api_url, params=params)

        if response.status_code == 200:
            self.traces =  response.json()
        else:
            print(f"Failed to retrieve traces. Status Code: {response.status_code}")
            self.traces = None
    
    def process_trace(self, root_span, spans, indent=""):
        span_id = root_span['id']
        span_name = root_span['name']
        span_kind = root_span['kind'] if 'kind' in root_span else "UNKNOWN"
        span_starttime = root_span['timestamp']
        span_duration = root_span['duration'] / 1000 if "duration" in root_span else 0 # convert to ms
        self.format_traces.append(f"{indent}SpanID: {span_id}, SpanName: {span_name}, SpanKind: {span_kind}, Timestamp:{span_starttime}, SpanDuration: {span_duration}ms\n")
        print(f"{indent}SpanID: {span_id}, SpanName: {span_name}, SpanKind: {span_kind}, Timestamp:{span_starttime}, SpanDuration: {span_duration}ms")

        children_spans = [span for span in spans if 'parentId' in span and span['parentId'] == span_id]
        children_spans = sorted(children_spans, key=lambda span: span['timestamp'])
    
        for child_span in children_spans:
            self.process_trace(child_span, spans, indent+"--")
    
    def process_traces(self):
        for trace in self.traces:
            root_span = [span for span in trace if "parentId" not in span][0]
            self.process_trace(root_span, trace)
            self.format_traces.append("==================================\n")
            print("==================================")
    
    def save_traces(self, path):
        if self.traces:
            with open(path+".json", "w") as outfile:
                    json.dump(self.traces, outfile, indent=2)
            with open(path+".txt", 'w') as outfile:
                    outfile.writelines(self.format_traces)


if __name__ == "__main__":
    trace_extractor = TraceExtractor("http://10.19.125.15:9411/zipkin/")

    end_time = datetime.now()
    start_time = end_time - timedelta(minutes=120)
    limit = 100

    trace_extractor.get_traces(start_time, end_time, limit)
    trace_extractor.process_traces()
    trace_extractor.save_traces("./traces/F1/buggy")
    
    

