/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.flowable.dmn.engine.test.runtime.drg;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.flowable.dmn.api.DmnDecisionService;
import org.flowable.dmn.engine.DmnEngine;
import org.flowable.dmn.engine.test.DmnDeployment;
import org.flowable.dmn.engine.test.FlowableDmnRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class DecisionServiceTest {

    @Rule
    public FlowableDmnRule flowableDmnRule = new FlowableDmnRule();

    @Test
    @DmnDeployment(resources = "org/flowable/dmn/engine/test/runtime/simple_decisionservice.dmn")
    public void executeDecisionServiceWithoutStartVariables() {
        DmnEngine dmnEngine = flowableDmnRule.getDmnEngine();
        DmnDecisionService dmnRuleService = dmnEngine.getDmnDecisionService();

        Map<String, Object> inputVariables = new HashMap<>();
        inputVariables.put("age", 18D);
        inputVariables.put("input11", "testString");
        inputVariables.put("input1", new Date());
        inputVariables.put("inputVariable1", 5D);
        inputVariables.put("riskcategory", "HIGH");

        List<Map<String, Object>> result = dmnRuleService.createExecuteDecisionBuilder()
            .decisionKey("decisionService1")
            .variables(inputVariables)
            .execute();

        Assert.assertNotNull(result);
    }
}
