/*
 * Copyright 2015 Livotov Labs Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.livotov.labs.webskel.api.v1.resources;

import com.google.inject.Inject;
import eu.livotov.labs.webskel.client.v1.model.LogEntry;
import eu.livotov.labs.webskel.client.v1.model.LogResult;
import eu.livotov.labs.webskel.core.dao.LogsDao;
import eu.livotov.labs.webskel.core.jpa.Log;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/log")
public class LogResource
{

    @Inject
    LogsDao logsDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public LogResult getLog()
    {
        List<Log> logs = logsDao.getLogs();
        LogResult result = new LogResult();

        for (Log log : logs)
        {
            result.getLog().add(new LogEntry(log.getTimestamp(), log.getMessage()));
        }

        return result;
    }
}
