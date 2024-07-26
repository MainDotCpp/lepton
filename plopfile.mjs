export default function Plopfile(plop) {
    plop.setGenerator('pojo', {
            description: 'application controller logic',
            prompts: [
                {
                    type: 'list',
                    name: 'module',
                    message: '模块',
                    choices: [
                        'lepton-module-framework/src/main/kotlin/com/leuan/lepton',
                        'lepton-module-customer/src/main/kotlin/com/leuan/lepton/customer'
                    ]
                },
                {
                    type: 'input',
                    name: 'biz',
                    message: '请输入业务名称(例如:user)'
                },
                {
                    type: 'input',
                    name: 'comment',
                    message: '请输入业务注释'
                }
            ],
            actions: [{
                type: 'addMany',
                base: '_templates/service',
                destination: '{{module}}',
                force: true,
                templateFiles: '_templates/service',
                data: {
                    package: 'com.leuan.lepton',
                }

            }]
        }
    )
}