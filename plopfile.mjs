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
                }

            ],
            data: {
                package: 'com.leuan.lepton',
            },
            actions: [{
                type: 'addMany',
                base: '_templates/service',
                destination: '{{module}}/{{name}}',
                force: true,
                templateFiles: '_templates/service'
            }]
        }
    )
}